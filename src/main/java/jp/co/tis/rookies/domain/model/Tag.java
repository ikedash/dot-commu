package jp.co.tis.rookies.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * タグエンティティ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Entity
@Table(name = "tag")
public class Tag {
    /** タグ名 */
    @Id
    private String tagName;

    /**
     * タグ名を取得。
     *
     * @return タグ名
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * タグ名の設定。
     *
     * @param tagName タグ名
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
