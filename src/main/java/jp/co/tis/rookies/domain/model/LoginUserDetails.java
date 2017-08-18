package jp.co.tis.rookies.domain.model;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * ユーザー認証データ格納用クラス。
 *
 * <p/>
 * {@link org.springframework.security.core.userdetails.User}の拡張クラス。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    /** 認証ユーザー */
    private final User user;

    /**
     * コンストラクタ
     *
     * @param user 認証ユーザー
     */
    public LoginUserDetails(User user) {
        super(user.getUserId().toString(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    /**
     * userを取得する。
     *
     * @return user
     */
    public User getUser() {
        return user;
    }
}
