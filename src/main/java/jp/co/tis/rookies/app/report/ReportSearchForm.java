package jp.co.tis.rookies.app.report;

import java.io.Serializable;

/**
 * レポートサーチフォーム。
 *
 * <p/>
 * 検索用のフォーム
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class ReportSearchForm implements Serializable {
    /** タイトル */
    private String title;

    /** 名前 */
    private String firstName;

    /** 名字 */
    private String lastName;

    /** タグ */
    private String tag;

    /**
     * タイトルの取得。
     *
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトルの設定。
     *
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 名前の取得。
     *
     * @return 名前
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 名前の設定。
     *
     * @param name 名前
     */
    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     * 名字の取得。
     *
     * @return 名字
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 名字の設定。
     *
     * @param lastName 名字
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * タグの取得。
     *
     * @return タグ
     */
    public String getTag() {
        return tag;
    }

    /**
     * タグの設定。
     *
     * @param tag タグ
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

}
