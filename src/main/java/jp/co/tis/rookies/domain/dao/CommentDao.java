package jp.co.tis.rookies.domain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
     * <ol>
     * <li>コメントIDを採番する</li>
     * <li>コメント投稿する</li>
     * </ol>
     *
     * @param comment コメント
     */
    public void insert(Comment comment) {
        Integer currentValue = npJdbcTemplate.queryForObject("SELECT MAX(COMMENT_ID) FROM COMMENTS", new HashMap<String, Object>(), Integer.class);
        comment.setCommentId(currentValue == null ? 1 : currentValue + 1);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append("COMMENTS(COMMENT_ID, REPORT_ID, USER_ID, COMMENT_BODY, CREATED_AT) ");
        sql.append("VALUES(:commentId, :reportId, :userId, :commentBody, :createdAt)");

        npJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(comment));
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

        npJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(comment));
    }

    /**
     * コメント削除。
     *
     * @param commentId コメントID
     */
    public void delete(Integer commentId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("commentId", commentId);

        npJdbcTemplate.update("DELETE FROM COMMENTS WHERE COMMENT_ID = :commentId", param);
    }

    /**
     * 主キー検索。
     *
     * @param commentId コメントID
     * @return 検索結果
     */
    public Comment findOne(Integer commentId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("commentId", commentId);

        RowMapper<Comment> mapper = new BeanPropertyRowMapper<Comment>(Comment.class);

        return npJdbcTemplate.queryForObject("SELECT * FROM COMMENTS WHERE COMMENT_ID=:commentId", param, mapper);
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
