package jp.co.tis.rookies.app.profile;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link ProfileForm} のテスト。
 *
 * @author Takayuki Emori
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileFormTest {
    /** バリデータ */
    private Validator validator;

    /** テスト対象クラス */
    private ProfileForm profileForm = new ProfileForm();

    /** 事前処理 */
    @Before
    public void setup() {
        profileForm = new ProfileForm();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     *  SetterとGetterのテスト
     */
    @Test
    public void testSetterAndGetter() {
        String lastName = "lastName";
        String firstName = "firstName";
        String birthplace = "birthplace";
        String favoriteFood = "favoriteFood";
        String hatedFood = "hatedFood";
        String bloodType = "bloodType";
        String birthday = "birthday";
        String country = "country";
        String introduction = "introduction";
        String image = "image";

        profileForm.setLastName(lastName);
        profileForm.setFirstName(firstName);
        profileForm.setBirthplace(birthplace);
        profileForm.setFavoriteFood(favoriteFood);
        profileForm.setHatedFood(hatedFood);
        profileForm.setBloodType(bloodType);
        profileForm.setBirthday(birthday);
        profileForm.setCountry(country);
        profileForm.setIntroduction(introduction);
        profileForm.setImage(image);

        // setterで設定した値とgetterで取得した値が等しいことを確認する。
        assertThat(profileForm.getLastName(), is(lastName));
        assertThat(profileForm.getFirstName(), is(firstName));
        assertThat(profileForm.getBirthplace(), is(birthplace));
        assertThat(profileForm.getFavoriteFood(), is(favoriteFood));
        assertThat(profileForm.getHatedFood(), is(hatedFood));
        assertThat(profileForm.getBloodType(), is(bloodType));
        assertThat(profileForm.getBirthday(), is(birthday));
        assertThat(profileForm.getCountry(), is(country));
        assertThat(profileForm.getIntroduction(), is(introduction));
        assertThat(profileForm.getImage(), is(image));
    }

    /**
     * 入力値が正常な値の場合のケース（正常系）
     */
    @Test
    public void testNormal() {
        profileForm.setLastName("lastName");
        profileForm.setFirstName("firstName");
        profileForm.setBirthday("0000/00/00");
        profileForm.setBirthplace("birthplace");
        profileForm.setFavoriteFood("favoriteFood");
        profileForm.setHatedFood("hatedFood");
        profileForm.setBloodType("bloodType");
        profileForm.setCountry("country");
        profileForm.setIntroduction("introduction");
        profileForm.setImage("image");

        // バリデート
        Set<ConstraintViolation<ProfileForm>> violations = validator.validate(profileForm);

        // 検証
        assertThat(violations, hasSize(0));
    }

    /**
     * 入力値がNullの場合のケース（異常系）
     */
    @Test
    public void testNullAbNormal() {
        profileForm.setLastName(null);
        profileForm.setFirstName(null);
        profileForm.setBirthday(null);
        profileForm.setBirthplace(null);
        profileForm.setFavoriteFood(null);
        profileForm.setHatedFood(null);
        profileForm.setBloodType(null);
        profileForm.setCountry(null);
        profileForm.setIntroduction(null);
        profileForm.setImage(null);

        // バリデート
        Set<ConstraintViolation<ProfileForm>> violations = validator.validate(profileForm);

        // 入力チェック違反数の検証
        assertThat(violations, hasSize(2));

        // 入力チェック違反の各プロパティの検証
        assertViolation(validator.validateProperty(profileForm, "lastName"), NotBlank.class, "必須項目です。");
        assertViolation(validator.validateProperty(profileForm, "firstName"), NotBlank.class, "必須項目です。");
    }

    /**
     * 入力値が空文字の場合のケース（異常系）
     */
    @Test
    public void testBlankAbNormal() {
        profileForm.setLastName("");
        profileForm.setFirstName("");
        profileForm.setBirthday("");
        profileForm.setBirthplace("");
        profileForm.setFavoriteFood("");
        profileForm.setHatedFood("");
        profileForm.setBloodType("");
        profileForm.setCountry("");
        profileForm.setIntroduction("");
        profileForm.setImage("");

        // バリデート
        Set<ConstraintViolation<ProfileForm>> violations = validator.validate(profileForm);

        // 入力チェック違反数の検証
        assertThat(violations, hasSize(2));

        // 入力チェック違反の各プロパティの検証
        assertViolation(validator.validateProperty(profileForm, "lastName"), NotBlank.class, "必須項目です。");
        assertViolation(validator.validateProperty(profileForm, "firstName"), NotBlank.class, "必須項目です。");
    }

    /**
     * 生年月日のフォーマットのテスト（異常系）
     */
    @Test
    public void testBirthdayFormatAbNormal() {
        List<String> inputs = Arrays.asList(
                "201/03/24", "20178/03/24", // 年の許容桁数-1、許容桁数+1
                "2017/0/24", "2017/034/24", // 月の許容桁数-1、許容桁数+1
                "2017/03/2", "2017/03/245", // 日の許容桁数-1、許容桁数+1
                "201a/03/24", // 数値でない文字が含まれる
                "2017/0324", "201703/24", // 区切り文字"/"が1つのみ
                "2017/03/24/", "/2017/03/24", // 区切り文字"/"が3つ
                "2017:03:24", // 区切り文字の種類が異なる
                "20170324"); // 区切り文字"/"が無い

        for (String input : inputs) {
            // 入力値の設定
            profileForm.setBirthday(input);

            // 検証
            assertViolation(validator.validateProperty(profileForm, "birthday"),
                    Pattern.class, "[" + input + "]は[yyyy/MM/dd]に一致しません。");
        }
    }

    /**
     * 入力チェック違反の結果検証を行う。
     *
     * @param violations validate結果
     * @param type 想定のアノテーション
     * @param msg 想定のエラーメッセージ
     */
    private void assertViolation(
            Set<ConstraintViolation<ProfileForm>> violations, Class<?> type, String msg) {

        // NGが1つであることの確認。
        assertThat(violations, hasSize(1));

        for (ConstraintViolation<ProfileForm> violation : violations) {
            // アノテーションの確認。
            assertThat(violation.getConstraintDescriptor().getAnnotation(), instanceOf(type));
            // エラーメッセージの確認。
            assertThat(violation.getMessage(), is(msg));
        }
    }
}
