package jp.co.tis.rookies.app.user;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ユーザーフォーム。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class UserForm implements Serializable {
    /** ユーザー名 */
    @NotBlank
    @Length(min = 3, max = 255)
    private String username;

    /** パスワード */
    @NotBlank
    @Length(min = 6, max = 255)
    private String password;

    /**
     * ユーザー名を取得する。
     *
     * @return ユーザー名
     */
    public String getUsername() {
        return username;
    }

    /**
     * ユーザー名を設定する。
     *
     * @param username ユーザー名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * パスワードを取得する。
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する。
     *
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
