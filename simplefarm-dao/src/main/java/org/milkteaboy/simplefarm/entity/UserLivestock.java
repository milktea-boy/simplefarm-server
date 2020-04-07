package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户畜舍实体
 */
public class UserLivestock {

    /**用户ID**/
    private Integer userId;
    /**建筑ID**/
    private Integer buildId;
    /**幼崽ID，-1为未养殖**/
    private Integer babyId;
    /**幼崽数量**/
    private Integer count;
    /**喂养次数**/
    private Integer feedCount;
    /**养殖时间**/
    private Date breedDatetime;
    /**货物数量**/
    private Integer goodsCount;

    public UserLivestock() {
    }

    public UserLivestock(Integer userId, Integer buildId, Integer babyId, Integer count, Integer feedCount, Date breedDatetime, Integer goodsCount) {
        this.userId = userId;
        this.buildId = buildId;
        this.babyId = babyId;
        this.count = count;
        this.feedCount = feedCount;
        this.breedDatetime = breedDatetime;
        this.goodsCount = goodsCount;
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

    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFeedCount() {
        return feedCount;
    }

    public void setFeedCount(Integer feedCount) {
        this.feedCount = feedCount;
    }

    public Date getBreedDatetime() {
        return breedDatetime;
    }

    public void setBreedDatetime(Date breedDatetime) {
        this.breedDatetime = breedDatetime;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
