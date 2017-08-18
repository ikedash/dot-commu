package jp.co.tis.rookies.domain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.tis.rookies.domain.model.Tag;

/**
 * タグDAO。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Repository
@Transactional
public class TagDao {
    /** DBアクセス用クラス */
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * 全検索。
     *
     * @return 検索結果。
     */
    public List<Tag> findAll() {
        // 検索用のSQLを設定する。
        String sql = "SELECT * FROM TAG ORDER BY TAG_NAME";

        // 検索を実行し、検索結果を返却する。
        return npJdbcTemplate.query(sql, new BeanPropertyRowMapper<Tag>(Tag.class));
    }

    /**
     * タグ名が存在するかどうかを返却する。
     *
     * @param tagName タグ名
     * @return true:存在する false:存在しない。
     */
    public boolean isExistTag(String tagName) {
        String sql = "SELECT count(*) FROM TAG WHERE TAG_NAME = :tagName";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tagName", tagName);

        int resultCount = npJdbcTemplate.queryForObject(sql, params, Integer.class);
        return resultCount != 0 ? true : false;
    }
}
