package jp.co.tis.rookies.domain.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.tis.rookies.domain.model.Profile;

/**
 * {@link ProfileDao} のテスト
 *
 * @author Takayuki Emori
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileDaoTest {
    /** プロフィールDAO */
    @Autowired
    private ProfileDao profileDao;

    /** DBアクセス用クラス */
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /** 事前処理 */
    @Before
    public void setup() {
        // データ削除
        deleteAllProfile();
        // データ投入（3件）
        for (int i = 1; i <= 3; i++) {
            profileDao.insert(createProfile(i));
        }
    }

    /**
     * {@link ProfileDao#searchByUserId} のテスト（正常系）。
     */
    @Test
    public void testSearchByUserIdNormal() {
        // 期待値、入力値
        int target = 2;
        Profile expected = createProfile(target);

        // 実施
        Profile actual = profileDao.findOne(target);

        // 検証
        assertProfile(actual, expected);
    }

    /**
     * {@link ProfileDao#searchByUserId} のテスト（異常系：検索対象なし）。
     */
    @Test(expected = DataAccessException.class)
    public void testSearchByUserIdAbNormal() {
        // 入力値
        int target = 4;

        // 実施
        profileDao.findOne(target);

        // DataAccessExceptionが発生しなかった場合は、テスト失敗。
        fail();
    }

    /**
     * {@link ProfileDao#create} のテスト（正常系）。
     */
    @Test
    public void testCreateNormal() {
        // 期待値、入力値
        int target = 5;
        Profile expected = createProfile(target);

        // 実施
        profileDao.insert(expected);

        // 検証
        Profile actual = profileDao.findOne(target);
        assertProfile(actual, expected);
    }

    /**
     * {@link ProfileDao#create} のテスト（異常系：一意制約違反）。
     */
    @Test(expected = DataAccessException.class)
    public void testCreateAbNormal() {
        // 期待値、入力値
        int target = 3;
        Profile expected = createProfile(target);

        // 実施
        profileDao.insert(expected);

        // DataAccessExceptionが発生しなかった場合は、テスト失敗。
        fail();
    }

    /**
     * {@link ProfileDao#update} のテスト（正常系）。
     */
    @Test
    public void testUpdateNormal() {
        // 期待値、入力値
        int target = 2;
        Profile expected = createProfile(4);
        expected.setUserId(target);

        // 実施
        profileDao.update(expected);

        // 検証
        Profile actual = profileDao.findOne(target);
        assertProfile(actual, expected);
    }

    /**
     * Profile生成。
     *
     * @param n 数値
     * @return Profile
     */
    private Profile createProfile(int n) {
        Profile profile = new Profile();
        profile.setUserId(n);
        profile.setFirstName("firstName" + n);
        profile.setLastName("lastName" + n);
        profile.setBirthplace("birthplace" + n);
        profile.setFavoriteFood("favoriteFood" + n);
        profile.setHatedFood("hatedFood" + n);
        profile.setBloodType("bloodType" + n);
        profile.setBirthday("birthday" + n);
        profile.setCountry("country" + n);
        profile.setIntroduction("introduction" + n);
        profile.setImage("image" + n);
        return profile;
    }

    /**
     * Profile内容検証。
     *
     * @param actual 実値
     * @param expected 期待値
     */
    private void assertProfile(Profile actual, Profile expected) {
        assertThat(actual.getUserId(), is(expected.getUserId()));
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
        assertThat(actual.getBirthplace(), is(expected.getBirthplace()));
        assertThat(actual.getFavoriteFood(), is(expected.getFavoriteFood()));
        assertThat(actual.getHatedFood(), is(expected.getHatedFood()));
        assertThat(actual.getBloodType(), is(expected.getBloodType()));
        assertThat(actual.getBirthday(), is(expected.getBirthday()));
        assertThat(actual.getCountry(), is(expected.getCountry()));
        assertThat(actual.getIntroduction(), is(expected.getIntroduction()));
        assertThat(actual.getImage(), is(expected.getImage()));
    }

    /**
     * プロフィール全件削除
     */
    private void deleteAllProfile() {
        npJdbcTemplate.update("DELETE FROM PROFILE", new HashMap<String, Object>());
    }
}