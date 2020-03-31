package org.milkteaboy.simplefarm.entity;

/**
 * 用户实体
 */
public class User {

    /**用户ID**/
    private Integer id;
    /**昵称**/
    private String nickname;
    /**等级**/
    private Integer level;
    /**经验**/
    private Integer exp;
    /**金币**/
    private Integer coin;
    /**账户**/
    private Account account;

    public User() {
    }

    public User(Integer id, String nickname, Integer level, Integer exp, Integer coin, Account account) {
        this.id = id;
        this.nickname = nickname;
        this.level = level;
        this.exp = exp;
        this.coin = coin;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
