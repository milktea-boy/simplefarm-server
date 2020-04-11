package org.milkteaboy.simplefarm.entity;

/**
 * 猎人小屋等级信息实体
 */
public class HunterLevelinfo {

    /**等级**/
    private Integer level;
    /**一次收获最少货物数量**/
    private Integer minReceiveCount;
    /**一次收获最大货物数量**/
    private Integer maxReceiveCount;
    /**收获时间间隔，单位秒**/
    private Integer receiveInterval;

    public HunterLevelinfo() {
    }

    public HunterLevelinfo(Integer level, Integer minReceiveCount, Integer maxReceiveCount, Integer receiveInterval) {
        this.level = level;
        this.minReceiveCount = minReceiveCount;
        this.maxReceiveCount = maxReceiveCount;
        this.receiveInterval = receiveInterval;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMinReceiveCount() {
        return minReceiveCount;
    }

    public void setMinReceiveCount(Integer minReceiveCount) {
        this.minReceiveCount = minReceiveCount;
    }

    public Integer getMaxReceiveCount() {
        return maxReceiveCount;
    }

    public void setMaxReceiveCount(Integer maxReceiveCount) {
        this.maxReceiveCount = maxReceiveCount;
    }

    public Integer getReceiveInterval() {
        return receiveInterval;
    }

    public void setReceiveInterval(Integer receiveInterval) {
        this.receiveInterval = receiveInterval;
    }
}
