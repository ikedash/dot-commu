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
 * コメントエンティティ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    /** コメントID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_SEQUENCE")
    @SequenceGenerator(name = "SEQ_GEN_SEQUENCE", sequenceName = "comment_seq", allocationSize = 1)
    private Integer commentId;

    /** レポートID */
    private Integer reportId;

    /** コメント内容 */
    @Column(length = 200)
    private String commentBody;

    /** ユーザーID */
    private Integer userId;

    /** 投稿日時 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * コメントIDを取得する。
     *
     * @return コメントID
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * コメントIDを設定する。
     *
     * @param commentId コメントID
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * レポートIDを取得する。
     *
     * @return レポートID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * レポートIDを設定する。
     *
     * @param reportId レポートID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * コメント内容を取得する。
     *
     * @return コメント内容
     */
    public String getCommentBody() {
        return commentBody;
    }

    /**
     * コメント内容を設定する。
     *
     * @param commentBody コメント内容
     */
    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    /**
     * ユーザーIDを取得する。
     *
     * @return ユーザーID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定する。
     *
     * @param userId ユーザーID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 投稿日時を取得する。
     *
     * @return 投稿日時
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 投稿日時を設定する。
     *
     * @param createdAt 投稿日時
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
