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
     * @param reportId 検索ID
     * @return 検索結果
     */
    public Report findOne(Integer reportId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        RowMapper<Report> mapper = new BeanPropertyRowMapper<Report>(Report.class);

        return npJdbcTemplate.queryForObject("SELECT * FROM REPORT WHERE REPORT_ID = :reportId", params, mapper);
    }

    /**
     * レポートに加えて、プロフィールの名字・名前を取得。
     *
     * @param reportId 検索ID
     * @return 検索結果
     */
    public Map<String, Object> findOneWithProfileName(Integer reportId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT R.REPORT_ID, R.TITLE, R.REPORT_BODY, ");
        sql.append("R.SATISFACTION, R.CAUSE, R.TAG, R.CREATED_AT, R.USER_ID, P.LAST_NAME, P.FIRST_NAME ");
        sql.append("FROM REPORT R INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
        sql.append("WHERE R.REPORT_ID = :reportId");

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

        // WHERE句が既に存在するかを判定する変数。
        boolean isNotExistWhere = true;

        if (hasValue(reportSearchForm.getLastName())) {
            sql.append("WHERE P.LAST_NAME = :lastName ");
            isNotExistWhere = false;
        }
        if (hasValue(reportSearchForm.getFirstName())) {
            if (isNotExistWhere) {
                sql.append("AND P.FIRST_NAME = :firstName ");
                isNotExistWhere = false;
            } else {
                sql.append("WHERE P.FIRST_NAME = :firstName ");
            }
        }
        if (hasValue(reportSearchForm.getTitle())) {
            if (isNotExistWhere) {
                sql.append("WHERE R.TITLE LIKE :title || '%' ");
                isNotExistWhere = false;
            } else {
                sql.append("AND R.TITLE LIKE :title || '%' ");
            }
        }
        if (hasValue(reportSearchForm.getTag())) {
            if (isNotExistWhere) {
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
     * </p>
     * PROFILEテーブルのカラム（名字、名前）が検索条件に入っている場合は、結合条件が必要となる。
     *
     * @param reportSearchForm レポートサーチフォーム
     * @return 件数
     */
    public int count(ReportSearchForm reportSearchForm) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(REPORT_ID) FROM REPORT R ");

        boolean isNotExistWhere = true;
        if (hasValue(reportSearchForm.getLastName())) {
            sql.append("INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
            sql.append("WHERE P.LAST_NAME = :lastName ");
            isNotExistWhere = false;
        }
        if (hasValue(reportSearchForm.getFirstName())) {
            if (isNotExistWhere) {
                sql.append("INNER JOIN PROFILE P ON  R.USER_ID = P.USER_ID ");
                sql.append("WHERE P.FIRST_NAME = :firstName ");
                isNotExistWhere = false;
            } else {
                sql.append("AND P.FIRST_NAME = :firstName ");
            }
        }
        if (hasValue(reportSearchForm.getTitle())) {
            if (isNotExistWhere) {
                sql.append("WHERE R.TITLE LIKE :title || '%' ");
                isNotExistWhere = false;
            } else {
                sql.append("AND R.TITLE LIKE :title || '%' ");
            }
        }
        if (hasValue(reportSearchForm.getTag())) {
            if (isNotExistWhere) {
                sql.append("WHERE R.TAG = :tag ");
            } else {
                sql.append("AND R.TAG = :tag ");
            }
        }

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reportSearchForm);

        return npJdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
    }

    /**
     * 登録。
     *
     * <ol>
     *   <li>レポートIDについて既に登録されている値をインクリメントして採番する</li>
     *   <li>レポートテーブルに登録する</li>
     * </ol>
     *
     * @param report 登録内容
     */
    public void insert(Report report) {
        Integer max = npJdbcTemplate.queryForObject("SELECT MAX(REPORT_ID) FROM REPORT", new MapSqlParameterSource(), Integer.class);
        report.setReportId(max == null ? 1 : max + 1);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append("REPORT(REPORT_ID, TITLE, REPORT_BODY, SATISFACTION, CAUSE, TAG, CREATED_AT, USER_ID) ");
        sql.append("VALUES(:reportId, :title, :reportBody, :satisfaction, :cause, :tag, :createdAt, :userId)");

        npJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(report));
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

        npJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(report));
    }

    /**
     * レポートの削除。
     *
     * @param reportId 削除するテーブルのユーザーID
     */
    public void delete(Integer reportId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        npJdbcTemplate.update("DELETE FROM REPORT WHERE REPORT_ID = :reportId", params);
    }

    /**
     * 文字列が値を持っているかを判定する。
     *
     * @param target 判定対象文字列
     * @return 値を持つ場合はtrue
     */
    private boolean hasValue(String target) {
        return !(target == null || target.isEmpty());
    }
}
