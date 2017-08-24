package jp.co.tis.rookies.app.report;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.tis.rookies.domain.model.Report;
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

        if (userManegementService.isNotSigninUser(report.getUserId())) {
            return "redirect:/report/detail?reportId=" + reportId;
        }

        reportForm.setTitle(report.getTitle());
        reportForm.setReportBody(report.getReportBody());
        reportForm.setSatisfaction(report.getSatisfaction());
        reportForm.setCause(report.getCause());
        reportForm.setTag(report.getTag());

        model.addAttribute("reportId", reportId);
        model.addAttribute("satisfactions", reportService.getSatisfactions());
        model.addAttribute("tags", reportService.getTags());

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
        Map<String, String> errors = reportService.validateReportForm(reportForm);
        if (errors != null) {
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
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, params = "redo")
    String updateRedo(@RequestParam Integer reportId, ReportForm reportForm, Model model) {
        model.addAttribute("reportId", reportId);
        model.addAttribute("satisfactions", reportService.getSatisfactions());
        model.addAttribute("tags", reportService.getTags());
        return "report/updateForm";
    }

    /**
     * 編集。
     *
     * @param reportId レポートID
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(@RequestParam Integer reportId, ReportForm reportForm, Model model) {
        Map<String, String> errors = reportService.validateReportForm(reportForm);
        if (errors != null) {
            model.addAttribute("errors", errors);
            return updateRedo(reportId, reportForm, model);
        }

        Report report = reportService.searchById(reportId);

        if (!userManegementService.isSigninUser(report.getUserId())) {
            return "redirect:/report/detail?reportId=" + reportId;
        }

        report.setTitle(reportForm.getTitle());
        report.setReportBody(reportForm.getReportBody());
        report.setSatisfaction(reportForm.getSatisfaction());
        report.setCause(reportForm.getCause());
        report.setTag(reportForm.getTag());

        reportService.update(report);

        return "redirect:/report/detail?reportId=" + reportId;
    }
}
