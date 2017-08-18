package jp.co.tis.rookies.domain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.tis.rookies.app.report.ReportSearchForm;
import jp.co.tis.rookies.domain.model.Report;

/**
 * レポートDAO。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Repository
@Transactional
public class ReportDao {
    /** DBアクセス用クラス */
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * 主キー検索。
     *
     * <p/>
     * 検索結果が0件の場合や、検索失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param reportId 検索ID
     * @return 検索結果
     */
    public Report findOne(Integer reportId) {
        // 実行するSQL。
        String sql = "SELECT * FROM REPORT WHERE REPORT_ID = :reportId";

        // SQLに埋め込むパラメータに値を設定。
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        // 検索結果をReportで取得する。
        RowMapper<Report> mapper = new BeanPropertyRowMapper<Report>(Report.class);

        return npJdbcTemplate.queryForObject(sql, params, mapper);
    }

    /**
     * レポートに加えて、プロフィールの名字・名前を取得。
     *
     * @param reportId 検索ID
     * @return 検索結果
     */
    public Map<String, Object> findOneWithProfileName(Integer reportId) {
        // 実行するSQL。
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT R.REPORT_ID, R.TITLE, R.REPORT_BODY, ");
        sql.append("R.SATISFACTION, R.CAUSE, R.TAG, R.CREATED_AT, R.USER_ID, P.LAST_NAME, P.FIRST_NAME ");
        sql.append("FROM REPORT R INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
        sql.append("WHERE R.REPORT_ID = :reportId");

        // SQLに埋め込むパラメータに値を設定。
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        return npJdbcTemplate.queryForMap(sql.toString(), params);
    }

    /**
     * ページ内検索。
     *
     * @param reportSearchForm レポートサーチフォーム
     * @return 検索結果
     */
    /**
     * ページ内検索。
     * @param reportSearchForm レポートサーチフォーム
     * @param pageFirst ページの最初のデータ番号
     * @param pageLast ページの最後のページ番号
     * @return 検索結果
     */
    public List<Map<String, Object>> findInPage(ReportSearchForm reportSearchForm, int pageFirst, int pageLast) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM(");
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY CREATED_AT DESC) RN, R.REPORT_ID, R.TITLE, R.REPORT_BODY, ");
        sql.append("R.SATISFACTION, R.CAUSE, R.TAG, R.CREATED_AT, R.USER_ID, P.LAST_NAME, P.FIRST_NAME ");
        sql.append("FROM REPORT R INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");

        // WHERE句が既に存在するかどうかを保持。
        boolean isExistWhere = false;

        // reportSearchFormのlastNameの値がnullでも空でもない場合、SQLを追加する。
        if (reportSearchForm.getLastName() != null && reportSearchForm.getLastName().length() > 0) {
            sql.append("WHERE P.LAST_NAME = :lastName ");
            // WHERE句の存在をTRUEにする。
            isExistWhere = true;
        }
        if (reportSearchForm.getFirstName() != null && reportSearchForm.getFirstName().length() > 0) {
            if (!isExistWhere) {
                // WHERE句が未だ存在しないので、WHERE句から始まるSQL。
                sql.append("WHERE P.FIRST_NAME = :firstName ");
                isExistWhere = true;
            } else {
                // WHERE句が既に存在するので、AND句から始まるSQL。
                sql.append("AND P.FIRST_NAME = :firstName ");
            }
        }
        if (reportSearchForm.getTitle() != null && reportSearchForm.getTitle().length() > 0) {
            if (!isExistWhere) {
                sql.append("WHERE R.TITLE LIKE :title || '%' ");
                isExistWhere = true;
            } else {
                sql.append("AND R.TITLE LIKE :title || '%' ");
            }
        }
        if (reportSearchForm.getTag() != null && reportSearchForm.getTag().length() > 0) {
            if (!isExistWhere) {
                sql.append("WHERE R.TAG = :tag ");
            } else {
                sql.append("AND R.TAG = :tag ");
            }
        }

        sql.append("ORDER BY CREATED_AT DESC)");
        sql.append("WHERE RN BETWEEN :pageFirst AND :pageLast");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageFirst", pageFirst);
        params.put("pageLast", pageLast);
        params.put("lastName", reportSearchForm.getLastName());
        params.put("firstName", reportSearchForm.getFirstName());
        params.put("title", reportSearchForm.getTitle());
        params.put("tag", reportSearchForm.getTag());

        return npJdbcTemplate.queryForList(sql.toString(), params);
    }

    /**
     * 件数のカウント。
     *
     * @param reportSearchForm レポートサーチフォーム
     * @return 件数
     */
    public int count(ReportSearchForm reportSearchForm) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM REPORT R ");

        boolean isExistWhere = false;
        if (reportSearchForm.getLastName() != null && reportSearchForm.getLastName().length() > 0) {
            // PROFILEテーブルのカラムが検索条件の場合は、結合が必要。
            sql.append("INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
            sql.append("WHERE P.LAST_NAME = :lastName ");
            isExistWhere = true;
        }
        if (reportSearchForm.getFirstName() != null && reportSearchForm.getFirstName().length() > 0) {
            if (!isExistWhere) {
                sql.append("INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
                sql.append("WHERE P.FIRST_NAME = :firstName ");
                isExistWhere = true;
            } else {
                sql.append("AND P.FIRST_NAME = :firstName ");
            }
        }
        if (reportSearchForm.getTitle() != null && reportSearchForm.getTitle().length() > 0) {
            if (!isExistWhere) {
                sql.append("WHERE R.TITLE LIKE :title || '%' ");
                isExistWhere = true;
            } else {
                sql.append("AND R.TITLE LIKE :title || '%' ");
            }
        }
        if (reportSearchForm.getTag() != null && reportSearchForm.getTag().length() > 0) {
            if (!isExistWhere) {
                sql.append("WHERE R.TAG = :tag ");
            } else {
                sql.append("AND R.TAG = :tag ");
            }
        }

        // SQLに埋め込むパラメータを、Beanから取得する。
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reportSearchForm);

        return npJdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
    }

    /**
     * 登録。
     *
     * @param report 登録内容
     */
    public void insert(Report report) {
        String sqlForReportId = "SELECT MAX(REPORT_ID) FROM REPORT";
        SqlParameterSource paramForReportId = new MapSqlParameterSource();

        // レポートIDの自動採番。
        Integer max = npJdbcTemplate.queryForObject(sqlForReportId, paramForReportId, Integer.class);
        report.setReportId(max == null ? 1 : max + 1);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append("REPORT(REPORT_ID, TITLE, REPORT_BODY, SATISFACTION, CAUSE, TAG, CREATED_AT, USER_ID) ");
        sql.append("VALUES(:reportId, :title, :reportBody, :satisfaction, :cause, :tag, :createdAt, :userId)");

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);

        npJdbcTemplate.update(sql.toString(), params);
    }

    /**
     * 更新。
     *
     * @param report 更新内容
     */
    public void update(Report report) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE REPORT ");
        sql.append("SET TITLE = :title, REPORT_BODY = :reportBody, SATISFACTION = :satisfaction, CAUSE = :cause, ");
        sql.append("TAG = :tag, CREATED_AT = :createdAt, USER_ID = :userId WHERE REPORT_ID = :reportId");

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);

        npJdbcTemplate.update(sql.toString(), params);
    }

    /**
     * レポートの削除。
     *
     * @param reportId 削除するテーブルのユーザーID
     */
    public void delete(Integer reportId) {
        String sql = "DELETE FROM REPORT WHERE REPORT_ID = :reportId";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        npJdbcTemplate.update(sql, params);
    }
}
