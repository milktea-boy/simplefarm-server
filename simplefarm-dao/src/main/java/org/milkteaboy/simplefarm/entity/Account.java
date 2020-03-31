package org.milkteaboy.simplefarm.entity;

/**
 * 账号实体
 */
public class Account {

    /**用户ID**/
    private Integer id;
    /**用户名**/
    private String username;
    /**用户密码**/
    private String password;

    public Account() {
    }

    public Account(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
