package org.milkteaboy.simplefarm.service.dto;

/**
 * 建筑信息DTO
 */
public class UserBuildInfo {

    /**建筑ID**/
    private int buildId;
    /**等级**/
    private int level;
    /**最大等级**/
    private int maxLevel;

    public UserBuildInfo() {
    }

    public UserBuildInfo(int buildId, int level, int maxLevel) {
        this.buildId = buildId;
        this.level = level;
        this.maxLevel = maxLevel;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
