package jp.co.tis.rookies.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tis.rookies.app.report.ReportSearchForm;
import jp.co.tis.rookies.domain.dao.CommentDao;
import jp.co.tis.rookies.domain.dao.ReportDao;
import jp.co.tis.rookies.domain.dao.TagDao;
import jp.co.tis.rookies.domain.model.Comment;
import jp.co.tis.rookies.domain.model.Report;
import jp.co.tis.rookies.domain.model.Tag;

/**
 * レポートサービス。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Service
public class ReportService {
    /** レポートDAO */
    @Autowired
    private ReportDao reportDao;

    /** タグDAO */
    @Autowired
    private TagDao tagDao;

    /** コメントDAO */
    @Autowired
    private CommentDao commentDao;

    /** ページの表示件数 */
    private static final int PAGE_SIZE = 10;

    /**
     * タグ一覧取得。
     *
     * @return 検索結果
     */
    public List<Tag> getTags() {
        return tagDao.findAll();
    }

    /**
     * タグの存在チェック。
     *
     * @param tagName タグ名
     * @return true:存在する。 false:存在しない。
     */
    public boolean isExistTag(String tagName) {
        return tagDao.isExistTag(tagName);
    }

    /**
     * 登録。
     *
     * @param report レポートエンティティ
     */
    public void create(Report report) {
        reportDao.insert(report);
    }

    /**
     * 更新。
     *
     * @param report レポートエンティティ
     */
    public void update(Report report) {
        reportDao.update(report);
    }

    /**
     * レポートIDによる検索。
     *
     * @param reportId レポートID
     * @return 検索結果
     */
    public Map<String, Object> searchForDetail(Integer reportId) {
        return reportDao.findOneWithProfileName(reportId);
    }

    /**
     * レポートIDによる検索。
     *
     * @param reportId レポートID
     * @return 検索結果
     */
    public Report searchById(Integer reportId) {
        return reportDao.findOne(reportId);
    }

    /**
     * リスト検索。
     *
     * @param reportSearchForm タグ
     * @param currentPage 表示するページ数
     * @return 検索結果
     */
    public List<Map<String, Object>> searchList(ReportSearchForm reportSearchForm, Integer currentPage) {
        int firstNumber = 1 + PAGE_SIZE * (currentPage - 1);
        int lastNumber = PAGE_SIZE * currentPage;
        return reportDao.findInPage(reportSearchForm, firstNumber, lastNumber);
    }

    /**
     * コメント登録。
     *
     * @param comment コメントエンティティ
     */
    public void createComment(Comment comment) {
        commentDao.insert(comment);
    }

    /**
     * コメント更新。
     *
     * @param comment コメントエンティティ
     */
    public void updateComment(Comment comment) {
        commentDao.update(comment);
    }

    /**
     * コメント削除。
     *
     * @param commentId コメントID
     */
    public void deleteComment(Integer commentId) {
        commentDao.delete(commentId);
    }

    /**
     * コメント表示。
     *
     * @param reportId レポートID
     * @return コメント一覧
     */
    public List<Map<String, Object>> searchComments(Integer reportId) {
        return commentDao.searchByReportId(reportId);
    }

    /**
     * コメント検索。
     *
     * @param commentId コメントID
     * @return 検索結果
     */
    public Comment searchCommentById(Integer commentId) {
        return commentDao.findOne(commentId);
    }

    /**
     * 現在ページがnullの時に1を返却。
     *
     * @param currentPage 現在のページ番号
     * @return ページ数
     */
    public Integer convertNullIntoOne(Integer currentPage) {
        return currentPage == null ? 1 : currentPage;
    }

    /**
     * データ最大件数から最大ページ数を計算。
     *
     * @param reportSearchForm レポートサーチフォーム
     * @return 最大ページ数
     */
    public int calcMaxPage(ReportSearchForm reportSearchForm) {
        double pageSize = PAGE_SIZE;
        double allSize = reportDao.count(reportSearchForm);

        return (int) Math.ceil(allSize / pageSize);
    }
}
