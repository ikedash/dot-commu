package jp.co.tis.rookies.app.profile;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * プロフィールフォーム。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class ProfileForm implements Serializable {
    /** 名字 */
    @NotBlank
    private String firstName;

    /** 名前 */
    @NotBlank
    private String lastName;

    /** 出身地 */
    private String birthplace;

    /** 好きな食べ物 */
    private String favoriteFood;

    /** 嫌いな食べ物 */
    private String hatedFood;

    /** 血液型 */
    private String bloodType;

    /** 生年月日 */
    @Pattern(regexp = "(|^(\\d{4})/(\\d{2})/(\\d{2})$)", message = "[${validatedValue}]は[yyyy/MM/dd]に一致しません。")
    private String birthday;

    /** いってみたい国 */
    private String country;

    /** 自己紹介 */
    private String introduction;

    /** 画像 */
    private String image;

    /**
     * 名字の取得。
     *
     * @return 名字
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 名字の設定。
     *
     * @param firstName 名字
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 名前の取得。
     *
     * @return 名前
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 名前の設定。
     *
     * @param lastName 名前
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 出身地の取得。
     *
     * @return 出身地
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * 出身地の設定。
     *
     * @param birthplace 出身地
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * 好きな食べ物の取得。
     *
     * @return 好きな食べ物
     */
    public String getFavoriteFood() {
        return favoriteFood;
    }

    /**
     * 好きな食べ物の設定。
     *
     * @param favoriteFood 好きな食べ物
     */
    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    /**
     * 嫌いな食べ物の取得。
     *
     * @return 嫌いな食べ物
     */
    public String getHatedFood() {
        return hatedFood;
    }

    /**
     * 嫌いな食べ物の設定。
     *
     * @param hatedFood 嫌いな食べ物
     */
    public void setHatedFood(String hatedFood) {
        this.hatedFood = hatedFood;
    }

    /**
     * 血液型の取得。
     *
     * @return 血液型
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * 血液型の設定。
     *
     * @param bloodType 血液型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * 生年月日の取得。
     *
     * @return 生年月日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生年月日の設定。
     *
     * @param birthday 生年月日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * いってみたい国の取得。
     *
     * @return いってみたい国
     */
    public String getCountry() {
        return country;
    }

    /**
     * いってみたい国の設定。
     *
     * @param country いってみたい国
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 自己紹介の取得。
     *
     * @return 自己紹介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 自己紹介の設定。
     *
     * @param introduction 自己紹介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 画像の取得。
     *
     * @return 画像
     */
    public String getImage() {
        return image;
    }

    /**
     * 画像の設定。
     *
     * @param image 画像
     */
    public void setImage(String image) {
        this.image = image;
    }
}
