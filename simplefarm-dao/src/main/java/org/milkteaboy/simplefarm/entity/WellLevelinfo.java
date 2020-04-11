package org.milkteaboy.simplefarm.entity;

/**
 * 水井等级信息实体
 */
public class WellLevelinfo {

    /**等级**/
    private Integer level;
    /**一次收获水滴数**/
    private Integer reapCount;
    /**收获时间间隔，单位秒**/
    private Integer reapInterval;
    /**最大水数量**/
    private Integer maxCount;

    public WellLevelinfo() {
    }

    public WellLevelinfo(Integer level, Integer reapCount, Integer reapInterval, Integer maxCount) {
        this.level = level;
        this.reapCount = reapCount;
        this.reapInterval = reapInterval;
        this.maxCount = maxCount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getReapCount() {
        return reapCount;
    }

    public void setReapCount(Integer reapCount) {
        this.reapCount = reapCount;
    }

    public Integer getReapInterval() {
        return reapInterval;
    }

    public void setReapInterval(Integer reapInterval) {
        this.reapInterval = reapInterval;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }
}
