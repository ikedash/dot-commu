package jp.co.tis.rookies.app.profile;

import java.util.HashMap;
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

import jp.co.tis.rookies.domain.model.Profile;
import jp.co.tis.rookies.domain.service.ProfileService;
import jp.co.tis.rookies.domain.service.UserManagementService;

/**
 * プロフィールコントローラ。
 *
 * @author Takayuki Emori
 * @since 1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    /** プロフィールサービス */
    @Autowired
    private ProfileService profileService;

    /** ユーザー管理サービス */
    @Autowired
    private UserManagementService userManegementService;

    /** バリデーション用クラス（値の妥当性チェック用クラス） */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * フォームをModelに追加するための設定。
     *
     * <p/>
     * ※ Spring特有の実装
     * <ol><li>
     * Modelに追加されるフォームのデフォルトの名前は、クラス名の先頭を小文字にした値となる。この例では "profileForm" が属性名になる。 <br>
     * 返却したオブジェクトは、model.addAttribute(form)相当の処理が実行されModelに追加される。
     * </li><li>
     * 名前を指定したい場合は、@ModelAttributeアノテーションのvalue属性に指定する。
     * </li><li>
     * 本処理が省略された場合にもModelにフォームは自動的に追加される。その場合、フォームの名前はデフォルトとなる。
     * </li></ol>
     *
     * @return プロフィールフォーム
     */
    @ModelAttribute
    public ProfileForm setUpForm() {
        return new ProfileForm();
    }

    /**
     * プロフィール内容。
     *
     * @param userId ユーザーID
     * @param model 画面に渡す値
     * @return プロフィール内容画面
     */
    @RequestMapping(method = RequestMethod.GET)
    String read(@RequestParam Integer userId, Model model) {
        // ユーザーIDに紐づくプロフィールを検索する。
        Profile profile = profileService.searchByUserId(userId);

        // 画面に渡す値を設定する。
        // ※ 値を画面に渡すためのSpringのクラス（Model）に対して、
        //    Model#addAttributeを利用して、検索結果をModelに追加している。
        model.addAttribute("profile", profile);

        return "profile/read";
    }

    /**
     * プロフィール編集画面の表示。
     *
     * @param userId ユーザーID
     * @param profileForm 入力値（フォーム）
     * @param model 画面に渡す値
     * @return プロフィール編集画面
     */
    @RequestMapping(value = "update")
    String updateForm(@RequestParam Integer userId, ProfileForm profileForm, Model model) {
        // サインインユーザーと更新対象が異なる場合、内容画面へ遷移する。
        if (userManegementService.isNotSigninUser(userId)) {
            return "redirect:/profile?userId=" + userId;
        }

        // ユーザーIDに紐づくプロフィールを検索する。
        Profile profile = profileService.searchByUserId(userId);

        // 検索結果をフォームに移送する。
        convertToProfileForm(profile, profileForm);

        // 画面に渡す値を設定する。
        model.addAttribute("userId", profile.getUserId());

        return "profile/updateForm";
    }

    /**
     * プロフィール編集処理。
     *
     * @param userId ユーザーID
     * @param profileForm 入力値（フォーム）
     * @param model 画面に渡す値
     * @return 正常時:プロフィール内容 エラー時:プロフィール編集画面
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(@RequestParam Integer userId, ProfileForm profileForm, Model model) {
        // サインインユーザーと更新対象が異なる場合、内容画面へ遷移する。
        if (userManegementService.isNotSigninUser(userId)) {
            return "redirect:/profile?userId=" + userId;
        }

        // 入力チェックを行う。
        Set<ConstraintViolation<ProfileForm>> results = validator.validate(profileForm);

        // エラーが存在する場合、エラーメッセージを取得し、プロフィール編集画面へ遷移する。
        if (results.size() > 0) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<ProfileForm> result : results) {
                errors.put(result.getPropertyPath().toString(), result.getMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("userId", userId);
            return "profile/updateForm";
        }

        // 更新前のプロフィールを取得する。
        Profile profile = profileService.searchByUserId(userId);

        // フォームの値をエンティティに移送し、更新を行う。
        profileService.update(convertToProfile(profileForm, profile));

        return "redirect:/profile?userId=" + userId;
    }

    /**
     * エンティティの値をフォームに移送する。
     *
     * @param source 移送元エンティティ
     * @param target 移送先フォーム
     */
    private void convertToProfileForm(Profile source, ProfileForm target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setBirthplace(source.getBirthplace());
        target.setFavoriteFood(source.getFavoriteFood());
        target.setHatedFood(source.getHatedFood());
        target.setBloodType(source.getBloodType());
        target.setBirthday(source.getBirthday());
        target.setCountry(source.getCountry());
        target.setIntroduction(source.getIntroduction());
        target.setImage(source.getImage());
    }

    /**
     * フォームの値をエンティティに移送する。
     *
     * @param source 移送元フォーム
     * @param target 移送先エンティティ
     * @return プロフィール
     */
    private Profile convertToProfile(ProfileForm source, Profile target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setBirthplace(source.getBirthplace());
        target.setFavoriteFood(source.getFavoriteFood());
        target.setHatedFood(source.getHatedFood());
        target.setBloodType(source.getBloodType());
        target.setBirthday(source.getBirthday());
        target.setCountry(source.getCountry());
        target.setIntroduction(source.getIntroduction());
        target.setImage(source.getImage());

        return target;
    }
}
