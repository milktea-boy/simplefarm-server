package org.milkteaboy.simplefarm.entity;

/**
 * 建筑配置实体
 */
public class Build {

    /**用户等级**/
    private Integer userLevel;
    /**建筑ID**/
    private Integer id;
    /**最大等级**/
    private Integer maxLevel;

    public Build() {
    }

    public Build(Integer userLevel, Integer id, Integer maxLevel) {
        this.userLevel = userLevel;
        this.id = id;
        this.maxLevel = maxLevel;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }
}
