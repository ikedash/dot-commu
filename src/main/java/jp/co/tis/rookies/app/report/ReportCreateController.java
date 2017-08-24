package jp.co.tis.rookies.app.report;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.tis.rookies.domain.model.Report;
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
        model.addAttribute("satisfactions", reportService.getSatisfactions());
        model.addAttribute("tags", reportService.getTags());
        return "report/createForm";
    }

    /**
     * 投稿内容確認表示。
     *
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
    String createConfirm(ReportForm reportForm, Model model) {
        Map<String, String> errors = reportService.validateReportForm(reportForm);
        if (errors != null) {
            model.addAttribute("errors", errors);
            return createRedo(reportForm, model);
        }

        return "report/createConfirm";
    }

    /**
     * 投稿フォーム再表示。
     *
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "redo")
    String createRedo(ReportForm reportForm, Model model) {
        model.addAttribute("satisfactions", reportService.getSatisfactions());
        model.addAttribute("tags", reportService.getTags());
        return "report/createForm";
    }

    /**
     * 投稿。
     *
     * @param reportForm レポートフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(ReportForm reportForm, Model model) {
        Map<String, String> errors = reportService.validateReportForm(reportForm);
        if (errors != null) {
            model.addAttribute("errors", errors);
            return createRedo(reportForm, model);
        }

        reportService.create(generateReport(reportForm));

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
        report.setTitle(reportForm.getTitle());
        report.setReportBody(reportForm.getReportBody());
        report.setSatisfaction(reportForm.getSatisfaction());
        report.setCause(reportForm.getCause());
        report.setTag(reportForm.getTag());
        report.setUserId(userManegementService.getSigninUserId());
        report.setCreatedAt(new Date());
        return report;
    }
}
