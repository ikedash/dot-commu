package jp.co.tis.rookies.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ユーザーエンティティ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class User {
    /** ユーザーID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_SEQUENCE")
    @SequenceGenerator(name = "SEQ_GEN_SEQUENCE", sequenceName = "users_seq", allocationSize = 1)
    private Integer userId;

    /** ユーザー名 */
    @Column(unique = true)
    private String username;

    /** 暗号化パスワード */
    @JsonIgnore
    private String encodedPassword;

    /**
     * ユーザーIDを取得。
     *
     * @return ユーザーID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
    * ユーザーIDの設定。
    *
    * @param userId ユーザーID
    */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * ユーザー名を取得。
     *
     * @return ユーザー名
     */
    public String getUsername() {
        return username;
    }

    /**
     * ユーザー名の設定
     * @param username ユーザー名。
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 暗号化パスワードの取得。
     *
     * @return 暗号化パスワード
     */
    public String getEncodedPassword() {
        return encodedPassword;
    }

    /**
     * 暗号化パスワードの設定。
     *
     * @param encodedPassword 暗号化パスワード
     */
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

}
