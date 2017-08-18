package jp.co.tis.rookies.domain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.tis.rookies.domain.model.User;

/**
 * ユーザーDAO。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * ユーザー名で検索。
     *
     * @param username ユーザー名
     * @return 検索結果
     */
    List<User> findByUsername(String username);

    /**
     * ユーザー名に該当するレコードのカウント。
     *
     * @param username ユーザー名
     * @return カウント
     */
    Long countByUsername(String username);
}
