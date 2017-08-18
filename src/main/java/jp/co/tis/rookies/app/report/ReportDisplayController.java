package jp.co.tis.rookies.app.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.tis.rookies.domain.model.Comment;
import jp.co.tis.rookies.domain.model.Tag;
import jp.co.tis.rookies.domain.service.ReportService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * レポート表示系コントローラ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
@RequestMapping("/report")
public class ReportDisplayController {
    /** レポートサービス */
    @Autowired
    private ReportService reportService;

    /** ユーザー管理サービス */
    @Autowired
    private UserManagementService userManegementService;

    /** バリデーション用クラス（値の妥当性チェック用クラス） */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * レポートサーチフォームの設定。
     *
     * @return レポートサーチフォーム
     */
    @ModelAttribute
    ReportSearchForm setUpReportSearchForm() {
        return new ReportSearchForm();
    }

    /**
     * コメントフォームの設定。
     *
     * @return コメントフォーム
     */
    @ModelAttribute
    CommentForm setUpCommentForm() {
        return new CommentForm();
    }

    /**
     * 一覧表示。
     *
     * @param currentPage 現在のページ番号
     * @param reportSearchForm サーチフォーム
     * @param model Viewに渡す値（をMap形式で保持したオブジェクト）
     * @return 遷移先のView名（拡張子を除いた名前）
     */
    @RequestMapping(method = RequestMethod.GET)
    String list(@RequestParam(value = "currentPage", required = false) Integer currentPage,
            ReportSearchForm reportSearchForm, Model model) {
        currentPage = reportService.convertNullIntoOne(currentPage);
        List<Map<String, Object>> reports = reportService.searchList(reportSearchForm, currentPage);
        List<Tag> tags = reportService.getTags();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("maxPage", reportService.calcMaxPage(reportSearchForm));
        model.addAttribute("reports", reports);
        model.addAttribute("tags", tags);

        return "report/list";
    }

    /**
     * 内容表示。
     *
     * @param reportId レポートID
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    String detail(@RequestParam Integer reportId, Model model) {
        Map<String, Object> report = reportService.searchForDetail(reportId);
        List<Map<String, Object>> comments = reportService.searchComments(reportId);

        model.addAttribute("report", report);
        model.addAttribute("comments", comments);
        model.addAttribute("myself", userManegementService.getSigninUserId());

        return "report/detail";
    }

    /**
     * コメント投稿フォーム表示。
     *
     * @param reportId レポートID
     * @param commentForm コメントフォーム
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "detail/createComment")
    public String createCommentForm(@RequestParam Integer reportId, CommentForm commentForm, Model model) {
        model.addAttribute("reportId", reportId);
        return "report/createCommentForm";
    }

    /**
     * コメント投稿。
     *
     * @param commentForm コメントフォーム
     * @param reportId レポートID
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "detail/createComment", method = RequestMethod.POST)
    public String createComment(CommentForm commentForm, @RequestParam Integer reportId, Model model) {
        // バリデーションを実行
        Set<ConstraintViolation<CommentForm>> results = validator.validate(commentForm);

        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<CommentForm> constraintViolation : results) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }

            model.addAttribute("errors", errors);
            model.addAttribute("reportId", reportId);

            return "report/createCommentForm";
        }

        Comment comment = new Comment();
        comment.setReportId(reportId);
        comment.setUserId(userManegementService.getSigninUserId());
        comment.setCommentBody(commentForm.getCommentBody());
        comment.setCreatedAt(new Date());

        reportService.createComment(comment);

        return "redirect:/report/detail?reportId=" + reportId;
    }

    /**
     * コメント編集フォーム表示。
     *
     * @param commentForm コメントフォーム
     * @param commentId コメントID
     * @param reportId レポートID
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "detail/updateComment")
    public String updateCommentForm(CommentForm commentForm, @RequestParam Integer commentId,
            @RequestParam Integer reportId, Model model) {
        Comment comment = reportService.searchCommentById(commentId);
        commentForm.setCommentBody(comment.getCommentBody());

        model.addAttribute("reportId", reportId);
        model.addAttribute("commentId", commentId);

        return "report/updateCommentForm";
    }

    /**
     * コメント編集。
     *
     * @param commentForm コメントフォーム
     * @param commentId コメントID
     * @param reportId レポートID
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "detail/updateComment", method = RequestMethod.POST, params = "confirm")
    public String updateComment(CommentForm commentForm, @RequestParam Integer commentId,
            @RequestParam Integer reportId, Model model) {
        // バリデーションを実行
        Set<ConstraintViolation<CommentForm>> results = validator.validate(commentForm);

        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<CommentForm> constraintViolation : results) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }

            model.addAttribute("errors", errors);
            model.addAttribute("reportId", reportId);
            model.addAttribute("commentId", commentId);

            return "report/updateCommentForm";
        }

        Comment comment = reportService.searchCommentById(commentId);
        comment.setCommentBody(commentForm.getCommentBody());

        reportService.updateComment(comment);

        return "redirect:/report/detail?reportId=" + reportId;
    }

    /**
     * コメント削除。
     *
     * @param commentId コメントID
     * @param reportId レポートID
     * @return 遷移先
     */
    @RequestMapping(value = "detail/deleteComment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam Integer commentId, @RequestParam Integer reportId) {
        reportService.deleteComment(commentId);
        return "redirect:/report/detail?reportId=" + reportId;
    }
}
