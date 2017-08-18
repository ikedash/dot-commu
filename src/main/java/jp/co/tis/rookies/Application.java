package jp.co.tis.rookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 実行クラス。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@SpringBootApplication
public class Application {
    /**
     * Spring起動用のメインメソッド。
     *
     * @param args 実行時引数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
