package org.milkteaboy.simplefarm.entity;

/**
 * 用户建筑信息
 */
public class UserBuild {

    /**用户ID**/
    private Integer userId;
    /**建筑ID**/
    private Integer buildId;
    /**建筑等级**/
    private Integer level;

    public UserBuild() {
    }

    public UserBuild(Integer userId, Integer buildId, Integer level) {
        this.userId = userId;
        this.buildId = buildId;
        this.level = level;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
