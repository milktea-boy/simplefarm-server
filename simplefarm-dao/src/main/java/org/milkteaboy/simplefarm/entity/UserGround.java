package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户地块实体
 */
public class UserGround {

    /**用户ID**/
    private Integer userId;
    /**地块序号**/
    private Integer index;
    /**种子**/
    private Seed seed;
    /**浇水次数**/
    private Integer waterCount;
    /**播种时间**/
    private Date sowDatetime;
    /**货物数量**/
    private Integer goodsCount;

    public UserGround() {
    }

    public UserGround(Integer userId, Integer index, Seed seed, Integer waterCount, Date sowDatetime, Integer goodsCount) {
        this.userId = userId;
        this.index = index;
        this.seed = seed;
        this.waterCount = waterCount;
        this.sowDatetime = sowDatetime;
        this.goodsCount = goodsCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(Integer waterCount) {
        this.waterCount = waterCount;
    }

    public Date getSowDatetime() {
        return sowDatetime;
    }

    public void setSowDatetime(Date sowDatetime) {
        this.sowDatetime = sowDatetime;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
