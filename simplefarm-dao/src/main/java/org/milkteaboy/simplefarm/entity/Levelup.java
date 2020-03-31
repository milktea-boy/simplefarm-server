package org.milkteaboy.simplefarm.entity;

/**
 * 升级配置实体
 */
public class Levelup {

    /**等级**/
    private Integer level;
    /**升级所需经验**/
    private Integer exp;

    public Levelup() {
    }

    public Levelup(Integer level, Integer exp) {
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
