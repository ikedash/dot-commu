package jp.co.tis.rookies.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.tis.rookies.domain.model.Profile;
import jp.co.tis.rookies.domain.model.User;
import jp.co.tis.rookies.domain.service.ProfileService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * ユーザー管理コントローラ。
 *
 * <p/>
 * 下記処理を担う
 * <ul>
 *   <li>サインイン</li>
 *   <li>サインアップ</li>
 * </ul>
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Controller
public class UserManagementController {
    /** ユーザー管理サービス */
    @Autowired
    private UserManagementService userManagementService;

    /** ユーザープロフィールサービス */
    @Autowired
    private ProfileService userProfileService;

    /**
     * ユーザーフォームの設定。
     *
     * @return ユーザーフォーム
     */
    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }

    /**
     * サインイン画面表示。
     *
     * @return 遷移先
     */
    @GetMapping(path = "signin")
    String signin() {
        return "user/signin";
    }

    /**
     * サインアップ画面表示。
     *
     * @return 遷移先
     */
    @RequestMapping(value = "signup", params = "form")
    String signup() {
        return "user/signup";
    }

    /**
     * サインアップ処理。
     *
     * @param userForm ユーザーフォーム
     * @param result 精査結果
     * @param model モデル
     * @return 遷移先
     */
    @PostMapping(path = "signup")
    String signup(@Validated UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/signup";
        }

        if (userManagementService.isExistUser(userForm.getUsername())) {
            model.addAttribute("used", true);
            return "user/signup";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEncodedPassword(new Pbkdf2PasswordEncoder().encode(userForm.getPassword()));
        // ユーザーを初期登録し、登録されたユーザーIDを取得
        Integer userId = userManagementService.createAndReturnUserId(user);

        Profile profile = new Profile();
        profile.setUserId(userId);
        // ユーザープロフィールの初期登録
        userProfileService.create(profile);

        model.addAttribute("joined", true);
        return "user/signin";
    }
}
