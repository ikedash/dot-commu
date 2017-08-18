package jp.co.tis.rookies.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Spring Security 設定クラス。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 権限設定
        http.authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signin").permitAll()
                .anyRequest().authenticated();

        // ログイン設定
        http.csrf().disable().formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/signin")
                .failureUrl("/signin?error=true")
                .defaultSuccessUrl("/report", true)
                .usernameParameter("username").passwordParameter("password");

        // ログアウト設定
        http.logout()
                .logoutSuccessUrl("/signin");
    }

    /**
     * パスワードハッシュ化の設定。
     *
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
