package jp.co.tis.rookies.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.tis.rookies.domain.dao.UserDao;
import jp.co.tis.rookies.domain.model.LoginUserDetails;
import jp.co.tis.rookies.domain.model.User;

/**
 * ユーザー管理サービス。
 *
 * <p/>
 * {@link org.springframework.security.core.userdetails.UserDetailsService}の拡張クラス。
 *
 * @author Shinya Ikeda
 * @since 1.0
 */
@Service
public class UserManagementService implements UserDetailsService {
    /** ユーザーDAO */
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userDao.findByUsername(username);
        if (userList.size() == 0) {
            throw new UsernameNotFoundException("The requested user is not found.");
        }
        User user = userList.get(0);
        return new LoginUserDetails(user);
    }

    /**
     * 指定したユーザー名のレコードが存在するか判定する。
     *
     * @param username ユーザー名
     * @return true:存在する false:存在しない
     */
    public boolean isExistUser(String username) {
        return userDao.countByUsername(username) > 0;
    }

    /**
     * ユーザー登録を行い、登録されたユーザーIDを返却する。
     *
     * @param user ユーザーエンティティ
     * @return ユーザーID
     */
    public Integer createAndReturnUserId(User user) {
        return userDao.save(user).getUserId();
    }

    /**
     * サインイン中のユーザーIDを取得する。
     *
     * @return ユーザーID
     */
    public Integer getSigninUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Integer.parseInt(userDetails.getUsername());
    }

    /**
     * サインイン中のユーザーである事を判定する。
     *
     * @param userId ユーザーID
     * @return true:サインイン中のユーザーである false:サインイン中のユーザーではない
     */
    public boolean isSigninUser(Integer userId) {
        return getSigninUserId().equals(userId);
    }

    /**
     * サインイン中のユーザーでない事を判定する。
     *
     * @param userId ユーザーID
     * @return true:サインイン中のユーザーである false:サインイン中のユーザーではない
     */
    public boolean isNotSigninUser(Integer userId) {
        return !isSigninUser(userId);
    }
}
