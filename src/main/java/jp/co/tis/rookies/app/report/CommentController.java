package jp.co.tis.rookies.app.report;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.tis.rookies.domain.model.Comment;
import jp.co.tis.rookies.domain.service.ReportService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * コメントコントローラ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
@RequestMapping("/report/detail")
public class CommentController {

    /** レポートサービス */
    @Autowired
    private ReportService reportService;

    /** ユーザー管理サービス */
    @Autowired
    private UserManagementService userManegementService;

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
     * コメント投稿フォーム表示。
     *
     * @param reportId レポートID
     * @param commentForm コメントフォーム
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "createComment")
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
    @RequestMapping(value = "createComment", method = RequestMethod.POST)
    public String createComment(CommentForm commentForm, @RequestParam Integer reportId, Model model) {
        Map<String, String> errors = reportService.validateCommentForm(commentForm);

        if (errors != null) {
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
    @RequestMapping(value = "updateComment")
    public String updateCommentForm(CommentForm commentForm, @RequestParam Integer commentId, @RequestParam Integer reportId, Model model) {
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
    @RequestMapping(value = "updateComment", method = RequestMethod.POST, params = "confirm")
    public String updateComment(CommentForm commentForm, @RequestParam Integer commentId, @RequestParam Integer reportId, Model model) {
        Map<String, String> errors = reportService.validateCommentForm(commentForm);

        if (errors != null) {
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
    @RequestMapping(value = "deleteComment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam Integer commentId, @RequestParam Integer reportId) {
        reportService.deleteComment(commentId);
        return "redirect:/report/detail?reportId=" + reportId;
    }
}
