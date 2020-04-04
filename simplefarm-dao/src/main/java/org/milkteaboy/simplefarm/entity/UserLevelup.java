package org.milkteaboy.simplefarm.entity;

/**
 * 用户升级信息实体
 */
public class UserLevelup {

    /**等级**/
    private Integer level;
    /**经验**/
    private Integer exp;

    public UserLevelup() {
    }

    public UserLevelup(Integer level, Integer exp) {
        this.level = level;
        this.exp = exp;
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
}
