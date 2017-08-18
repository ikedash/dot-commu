package jp.co.tis.rookies.app.report;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * レポートフォーム。
 *
 * <p/>
 * 投稿、編集共通のフォーム
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class ReportForm implements Serializable {
    /** タイトル */
    @NotBlank
    @Length(max = 50)
    private String title;

    /** 内容 */
    @NotBlank
    @Length(max = 500)
    private String reportBody;

    /** 満足度 */
    @NotBlank
    private String satisfaction;

    /** 理由 */
    @NotBlank
    @Length(max = 200)
    private String cause;

    /** タグ */
    @NotBlank
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
     * 内容の取得。
     *
     * @return 内容
     */
    public String getReportBody() {
        return reportBody;
    }

    /**
     * 内容の設定。
     *
     * @param reportBody 内容
     */
    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    /**
     * 満足度の取得。
     *
     * @return 満足度
     */
    public String getSatisfaction() {
        return satisfaction;
    }

    /**
     * 満足度の設定。
     *
     * @param satisfaction 満足度
     */
    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    /**
     * 理由の取得。
     *
     * @return 理由
     */
    public String getCause() {
        return cause;
    }

    /**
     * 理由の設定。
     *
     * @param cause 理由
     */
    public void setCause(String cause) {
        this.cause = cause;
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
