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

import jp.co.tis.rookies.domain.model.Report;
import jp.co.tis.rookies.domain.model.Tag;
import jp.co.tis.rookies.domain.service.ReportService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * レポート投稿コントローラ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
@RequestMapping("/report")
public class ReportCreateController {
    /** レポートサービス */
    @Autowired
    private ReportService reportService;

    /** ユーザー管理サービス */
    @Autowired
    private UserManagementService userManegementService;

    /** バリデーション用クラス（値の妥当性チェック用クラス） */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * フォームをModelに追加するための設定。
     *
     * @return レポートフォーム
     */
    @ModelAttribute
    public ReportForm setUpForm() {
        return new ReportForm();
    }

    /**
     * 投稿フォーム表示。
     *
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create")
    String createForm(Model model) {
        // 画面に渡すタグ一覧を取得し、Modelに設定
        List<Tag> tags = reportService.getTags();
        model.addAttribute("tags", tags);

        return "report/createForm";
    }

    /**
     * 投稿内容確認表示。
     *
     * @param reportForm フォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
    String createConfirm(ReportForm reportForm, Model model) {
        // バリデーションを実行
        Set<ConstraintViolation<ReportForm>> results = validator.validate(reportForm);

        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<ReportForm> constraintViolation : results) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }
            model.addAttribute("errors", errors);
            return createRedo(reportForm, model);
        }

        // 登録予定のタグが、タグテーブルに存在するかのチェック処理
        if (!reportService.isExistTag(reportForm.getTag())) {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("tag", "[" + reportForm.getTag() + "]は存在しないタグです。");

            model.addAttribute("errors", errors);
            return createRedo(reportForm, model);
        }

        return "report/createConfirm";
    }

    /**
     * 投稿フォーム再表示。
     *
     * @param form フォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "redo")
    String createRedo(ReportForm form, Model model) {
        // タグ一覧を取得し、画面に渡す情報（タグ一覧と入力内容）をModelに設定
        List<Tag> tags = reportService.getTags();
        model.addAttribute("tags", tags);

        return "report/createForm";
    }

    /**
     * 投稿。
     *
     * @param form フォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(ReportForm form, Model model) {
        // バリデーションを実行
        Set<ConstraintViolation<ReportForm>> results = validator.validate(form);

        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<ReportForm> constraintViolation : results) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }
            model.addAttribute("errors", errors);
            return createRedo(form, model);
        }

        reportService.create(generateReport(form));

        return "redirect:/report/create?complete";
    }

    /**
     * 投稿完了表示。
     *
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", params = "complete")
    String createComplete() {
        return "report/createComplete";
    }

    /**
     * レポートフォームを元にレポートエンティティを生成する。
     *
     * <ul>
     *   <li>フォームに存在する項目はそのままエンティティに設定</li>
     *   <li>投稿者へ認証情報から取得したユーザ名を設定</li>
     *   <li>投稿日時へシステム日時を設定</li>
     * </ul>
     *
     * @param reportForm レポートフォーム
     * @return レポートエンティティ
     */
    private Report generateReport(ReportForm reportForm) {
        Report report = new Report();
        // ReportFormの値をReportに詰める。
        report.setTitle(reportForm.getTitle());
        report.setReportBody(reportForm.getReportBody());
        report.setSatisfaction(reportForm.getSatisfaction());
        report.setCause(reportForm.getCause());
        report.setTag(reportForm.getTag());

        // 認証情報からユーザ名を設定する
        report.setUserId(userManegementService.getSigninUserId());
        report.setCreatedAt(new Date());
        return report;
    }
}
