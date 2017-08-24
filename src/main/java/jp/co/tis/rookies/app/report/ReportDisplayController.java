package jp.co.tis.rookies.app.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    /** デフォルトページ番号 */
    private static final int DEFAULT_PAGE_NUMBER = 1;

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
     * 一覧表示。
     *
     * @param currentPage 現在のページ番号
     * @param reportSearchForm サーチフォーム
     * @param model Viewに渡す値
     * @return 遷移先のView名
     */
    @RequestMapping(method = RequestMethod.GET)
    String list(@RequestParam(value = "currentPage", required = false) Integer currentPage, ReportSearchForm reportSearchForm, Model model) {
        if (currentPage == null) {
            currentPage = DEFAULT_PAGE_NUMBER;
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("reports", reportService.searchList(reportSearchForm, currentPage));
        model.addAttribute("maxPage", reportService.calcMaxPage(reportSearchForm));
        model.addAttribute("tags", reportService.getTags());

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
        model.addAttribute("report", reportService.searchForDetail(reportId));
        model.addAttribute("comments", reportService.searchComments(reportId));
        model.addAttribute("myself", userManegementService.getSigninUserId());

        return "report/detail";
    }
}
