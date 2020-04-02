package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * 账号DAO
 */
public interface AccountDao {

    int insert(Account account);
    int updatePassword(Account account);
    Account selectById(Integer id);
    Account selectByUsername(String username);
    Account selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
