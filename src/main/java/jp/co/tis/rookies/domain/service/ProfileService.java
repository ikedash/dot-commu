package jp.co.tis.rookies.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tis.rookies.domain.dao.ProfileDao;
import jp.co.tis.rookies.domain.model.Profile;

/**
 * プロフィールサービス。
 *
 * @author Takayuki Emori
 * @since 1.0
 */
@Service
public class ProfileService {
    /** プロフィールDAO */
    @Autowired
    private ProfileDao profileDao;

    /**
     * ユーザーIDに紐づくプロフィールを検索する。
     *
     * <p/>
     * 検索結果が0件の場合や、検索失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param userId ユーザーID
     * @return 検索結果
     */
    public Profile searchByUserId(Integer userId) {
        return profileDao.findOne(userId);
    }

    /**
     * 初期登録を行う。
     *
     * <p/>
     * 登録失敗の場合は、DataAccessExceptionがスローされる。
     *
     * @param profile プロフィール
     */
    public void create(Profile profile) {
        profileDao.insert(profile);
    }

    /**
     * 更新を行う。
     *
     * @param profile プロフィール
     */
    public void update(Profile profile) {
        profileDao.update(profile);
    }
}
