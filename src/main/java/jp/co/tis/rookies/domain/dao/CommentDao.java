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

import jp.co.tis.rookies.domain.model.Comment;

/**
 * コメントDAO。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Repository
@Transactional
public class CommentDao {
    /** DBアクセス用クラス */
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * コメント投稿。
     *
     * @param comment コメント
     */
    public void insert(Comment comment) {
        String sqlForCommentId = "SELECT MAX(COMMENT_ID) FROM COMMENTS";
        SqlParameterSource paramForCommentId = new MapSqlParameterSource();

        // コメントIDの採番を行う
        Integer currentValue = npJdbcTemplate.queryForObject(sqlForCommentId, paramForCommentId, Integer.class);
        int nextValue = currentValue == null ? 1 : currentValue + 1;
        comment.setCommentId(nextValue);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append("COMMENTS(COMMENT_ID, REPORT_ID, USER_ID, COMMENT_BODY, CREATED_AT) ");
        sql.append("VALUES(:commentId, :reportId, :userId, :commentBody, :createdAt)");

        // SQLに引き渡すパラメータ
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        npJdbcTemplate.update(sql.toString(), param);
    }

    /**
     * コメント編集。
     *
     * @param comment コメント
     */
    public void update(Comment comment) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE COMMENTS ");
        sql.append("SET COMMENT_ID = :commentId, COMMENT_BODY = :commentBody, USER_ID = :userId, ");
        sql.append("CREATED_AT = :createdAt, REPORT_ID = :reportId WHERE COMMENT_ID = :commentId");

        // SQLに引き渡すパラメータ
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        npJdbcTemplate.update(sql.toString(), param);
    }

    /**
     * コメント削除。
     *
     * @param commentId コメントID
     */
    public void delete(Integer commentId) {
        String sql = "DELETE FROM COMMENTS WHERE COMMENT_ID = :commentId";

        // SQLに引き渡すパラメータ
        SqlParameterSource param = new MapSqlParameterSource("commentId", commentId);

        npJdbcTemplate.update(sql, param);
    }

    /**
     * 主キー検索。
     *
     * @param commentId コメントID
     * @return 検索結果
     */
    public Comment findOne(Integer commentId) {
        String sql = "SELECT * FROM COMMENTS WHERE COMMENT_ID=:commentId";

        // SQLに引き渡すパラメータ
        SqlParameterSource param = new MapSqlParameterSource("commentId", commentId);

        // SELECT結果を格納するオブジェクト
        RowMapper<Comment> mapper = new BeanPropertyRowMapper<Comment>(Comment.class);

        return npJdbcTemplate.queryForObject(sql, param, mapper);
    }

    /**
     * レポートID完全一致検索。
     *
     * @param reportId レポートID
     * @return 検索結果
     */
    public List<Map<String, Object>> searchByReportId(Integer reportId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.COMMENT_ID, C.REPORT_ID, C.COMMENT_BODY, C.USER_ID, P.FIRST_NAME, P.LAST_NAME, P.IMAGE ");
        sql.append("FROM COMMENTS C INNER JOIN PROFILE P ON  C.USER_ID = P.USER_ID ");
        sql.append("WHERE C.REPORT_ID = :reportId ORDER BY C.CREATED_AT ASC");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportId", reportId);

        return npJdbcTemplate.queryForList(sql.toString(), params);
    }
}
