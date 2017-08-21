package jp.co.tis.rookies.domain.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.tis.rookies.domain.model.Profile;

/**
 * プロフィールDAO。
 *
 * @author Takayuki Emori
 * @since 1.0
 */
@Repository
@Transactional
public class ProfileDao {
    /** DBアクセス用クラス */
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * 主キー検索を行う。
     *
     * <p/>
     * 検索結果が0件の場合や、検索失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param userId ユーザーID
     * @return 該当ユーザーのプロフィール
     */
    public Profile findOne(Integer userId) {
        // 検索用のSQLを設定する。
        String sql = "SELECT * FROM PROFILE WHERE USER_ID=:userId";

        // SQLに埋め込むパラメータを設定する。
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);

        // 検索を実行し、検索結果を返却する。
        return npJdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<Profile>(Profile.class));
    }

    /**
     * プロフィールを登録する。
     *
     * <p/>
     * 登録失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param profile 登録プロフィール
     */
    public void insert(Profile profile) {
        // 登録用のSQLを設定する。
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append("PROFILE(user_id, first_name, last_name, birthplace, favorite_food, hated_food, ");
        sql.append("blood_type, birthday, country, introduction, image)");
        sql.append("VALUES(:userId, :firstName, :lastName, :birthplace, :favoriteFood, :hatedFood, ");
        sql.append(":bloodType, :birthday, :country, :introduction, :image)");

        // SQLに埋め込むパラメータを設定する。
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(profile);

        // 登録を実行する。
        npJdbcTemplate.update(sql.toString(), param);
    }

    /**
     * プロフィールを更新する。
     *
     * <p/>
     * 更新失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param profile 更新プロフィール
     */
    public void update(Profile profile) {
        // 更新用のSQLを設定する。
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PROFILE ");
        sql.append("SET USER_ID=:userId, FIRST_NAME=:firstName, LAST_NAME=:lastName, BIRTHPLACE=:birthplace, ");
        sql.append("FAVORITE_FOOD=:favoriteFood, HATED_FOOD=:hatedFood, BLOOD_TYPE=:bloodType, BIRTHDAY=:birthday, ");
        sql.append("COUNTRY=:country, INTRODUCTION=:introduction, IMAGE=:image WHERE USER_ID=:userId");

        // SQLに埋め込むパラメータを設定する。
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(profile);

        // 更新を実行する。
        npJdbcTemplate.update(sql.toString(), param);
    }
}
