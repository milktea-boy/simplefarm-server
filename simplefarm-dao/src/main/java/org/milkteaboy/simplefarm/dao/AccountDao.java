package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.Account;

/**
 * 账号DAO
 */
public interface AccountDao {

    void insert(Account account);
    void updatePassword(Account account);
    Account selectById(Integer id);
    Account selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
