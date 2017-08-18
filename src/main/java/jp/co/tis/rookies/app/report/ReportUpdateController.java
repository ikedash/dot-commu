package jp.co.tis.rookies.app.report;

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

import jp.co.tis.rookies.domain.model.Report;
import jp.co.tis.rookies.domain.model.Tag;
import jp.co.tis.rookies.domain.service.ReportService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * レポート編集コントローラ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
@RequestMapping("/report")
public class ReportUpdateController {
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
     * 編集フォーム表示。
     *
     * @param reportId レポートID
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "update")
    String updateForm(@RequestParam Integer reportId, ReportForm reportForm, Model model) {
        Report report = reportService.searchById(reportId);

        // サインインユーザーと投稿者が異なる場合、内容画面へ遷移させる
        if (!userManegementService.isSigninUser(report.getUserId())) {
            return "redirect:/report/{id}";
        }

        // ReportからReportFormに値を移送する。
        reportForm.setTitle(report.getTitle());
        reportForm.setReportBody(report.getReportBody());
        reportForm.setSatisfaction(report.getSatisfaction());
        reportForm.setCause(report.getCause());
        reportForm.setTag(report.getTag());

        List<Tag> tags = reportService.getTags();
        model.addAttribute("reportId", reportId);
        model.addAttribute("tags", tags);

        return "report/updateForm";
    }

    /**
     * 編集内容確認表示。
     *
     * @param reportId レポートID
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, params = "confirm")
    String updateConfirm(@RequestParam Integer reportId, ReportForm reportForm, Model model) {

        // バリデーションを実行
        Set<ConstraintViolation<ReportForm>> result = validator.validate(reportForm);

        if (result.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<ReportForm> constraintViolation : result) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }
            model.addAttribute("errors", errors);
            return updateRedo(reportId, reportForm, model);
        }

        model.addAttribute("reportId", reportId);

        return "report/updateConfirm";
    }

    /**
     * 編集フォーム再表示。
     *
     * @param reportId レポートID
     * @param reportForm レポートフォーム
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, params = "redo")
    String updateRedo(@RequestParam Integer reportId, ReportForm reportForm, Model model) {
        List<Tag> tags = reportService.getTags();
        model.addAttribute("reportId", reportId);
        model.addAttribute("tags", tags);

        return "report/updateForm";
    }

    /**
     * 編集。
     *
     * @param reportId レポートID
     * @param reportForm レポートフォーム
     * @param model モデル
     * @return 遷移先
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(@RequestParam Integer reportId, ReportForm reportForm, Model model) {

        // バリデーションを実行
        Set<ConstraintViolation<ReportForm>> result = validator.validate(reportForm);

        if (result.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<ReportForm> constraintViolation : result) {
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            }
            model.addAttribute("errors", errors);
            return updateRedo(reportId, reportForm, model);
        }

        Report report = reportService.searchById(reportId);

        // サインインユーザーと投稿者が異なる場合、内容画面へ遷移させる
        if (!userManegementService.isSigninUser(report.getUserId())) {
            return "redirect:/report/{id}";
        }

        // ReportFormからReportに値を移送する。
        report.setTitle(reportForm.getTitle());
        report.setReportBody(reportForm.getReportBody());
        report.setSatisfaction(reportForm.getSatisfaction());
        report.setCause(reportForm.getCause());
        report.setTag(reportForm.getTag());

        reportService.update(report);

        return "redirect:/report/detail?reportId=" + reportId;
    }

}
