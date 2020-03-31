package org.milkteaboy.simplefarm.entity;

/**
 * 猎人小屋配置实体
 */
public class Hunter {

    /**等级**/
    private Integer level;
    /**价格**/
    private Integer price;
    /**一次收获最小收获数量**/
    private Integer minReceiveCount;
    /**一次收获最大收获数量**/
    private Integer maxReceiveCount;
    /**收获时间间隔，单位秒**/
    private Integer receiveInterval;

    public Hunter() {
    }

    public Hunter(Integer level, Integer price, Integer minReceiveCount, Integer maxReceiveCount, Integer receiveInterval) {
        this.level = level;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
