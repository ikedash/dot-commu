package jp.co.tis.rookies.util;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * エラー画面コントローラ。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
public class ErrController implements ErrorController {
    /** エラー画面パス */
    private static final String PATH = "/error";

    /**
     * エラー画面へ遷移する。
     *
     * @return エラー画面
     */
    @RequestMapping(PATH)
    String home() {
        return "common/error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
