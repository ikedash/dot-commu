package jp.co.tis.rookies.domain.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tis.rookies.app.report.ReportForm;
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
     * 満足度一覧取得。
     *
     * @return 満足度一覧
     */
    public List<String> getSatisfactions() {
        return Arrays.asList("VeryGood", "Good", "Medium", "Bad", "VeryBad");
    }

    /**
     * タグ一覧取得。
     *
     * @return タグ一覧の検索結果
     */
    public List<Tag> getTags() {
        return tagDao.findAll();
    }

    /**
     * レポートフォームの入力内容を精査する処理。
     *
     * <ol>
     * <li>項目精査を行う</li>
     * <li>DB精査（タグ存在チェック）を行う</li>
     * </ol>
     *
     * @param reportForm レポートフォーム
     * @return エラー内容を返却する。エラーが存在しない場合はnullを返却する。
     */
    public Map<String, String> validateReportForm(ReportForm reportForm) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ReportForm>> results = validator.validate(reportForm);
        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            results.forEach(constraintViolation -> errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
            return errors;
        }
    
        if (!tagDao.isExistTag(reportForm.getTag())) {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("tag", "[" + reportForm.getTag() + "]は存在しないタグです。");
            return errors;
        }
    
        return null;
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
     * レポートIDによる検索。
     *
     * @param reportId レポートID
     * @return 検索結果
     */
    public Report searchById(Integer reportId) {
        return reportDao.findOne(reportId);
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
     * レポートIDによる検索。
     *
     * @param reportId レポートID
     * @return 検索結果
     */
    public Map<String, Object> searchForDetail(Integer reportId) {
        return reportDao.findOneWithProfileName(reportId);
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
}
