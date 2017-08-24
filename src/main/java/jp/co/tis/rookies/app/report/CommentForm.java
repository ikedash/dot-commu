package jp.co.tis.rookies.app.report;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * コメントフォーム。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public class CommentForm implements Serializable {
    /** コメント内容 */
    @NotBlank
    @Length(max = 200, message = "{max}文字以内で入力してください。")
    private String commentBody;

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
}
