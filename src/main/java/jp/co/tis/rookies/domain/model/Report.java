package jp.co.tis.rookies.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * レポートエンティティ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Entity
@Table(name = "report")
public class Report implements Serializable {
    /** レポートID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_SEQUENCE")
    @SequenceGenerator(name = "SEQ_GEN_SEQUENCE", sequenceName = "report_seq", allocationSize = 1)
    private Integer reportId;

    /** タイトル */
    @Column(length = 50)
    private String title;

    /** 内容 */
    @Column(length = 500)
    private String reportBody;

    /** 満足度 */
    @Column(length = 20)
    private String satisfaction;

    /** 理由 */
    @Column(length = 200)
    private String cause;

    /** タグ */
    @Column(length = 30)
    private String tag;

    /** 投稿日時 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /** ユーザーID */
    private Integer userId;

    /**
     * レポートIDの取得。
     *
     * @return レポートID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * レポートIDの設定。
     *
     * @param reportId レポートID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

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
     * レポート内容の取得。
     *
     * @return レポート内容
     */
    public String getReportBody() {
        return reportBody;
    }

    /**
     * レポート内容の設定。
     *
     * @param reportBody レポート内容
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
     * 満足度の理由の取得。
     *
     * @return 満足度の理由
     */
    public String getCause() {
        return cause;
    }

    /**
     * 満足度の理由の設定。
     *
     * @param cause 満足度の理由
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

    /**
     * 登録日時の取得。
     *
     * @return 登録日時
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 登録日時の設定。
     *
     * @param createdAt 登録日時
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * ユーザーIDの取得。
     *
     * @return ユーザーID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * ユーザーIDの設定。
     * @param userId ユーザーID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
