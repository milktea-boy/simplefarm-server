package org.milkteaboy.simplefarm.service.dto;

/**
 * 用户详细信息DTO
 */
public class UserDetailInfo {

    /**用户ID**/
    private int userId;
    /**昵称**/
    private String nickname;
    /**用户等级**/
    private int level;
    /**当前经验**/
    private int exp;
    /**升级所需经验**/
    private int needExp;
    /**金币**/
    private int coin;

    public UserDetailInfo() {
    }

    public UserDetailInfo(int userId, String nickname, int level, int exp, int needExp, int coin) {
        this.userId = userId;
        this.nickname = nickname;
        this.level = level;
        this.exp = exp;
        this.needExp = needExp;
        this.coin = coin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNeedExp() {
        return needExp;
    }

    public void setNeedExp(int needExp) {
        this.needExp = needExp;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
