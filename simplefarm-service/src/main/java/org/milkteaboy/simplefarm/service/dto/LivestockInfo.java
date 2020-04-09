package org.milkteaboy.simplefarm.service.dto;

import java.util.Date;
import java.util.List;

/**
 * 畜舍信息DTO
 */
public class LivestockInfo {

    /**建筑等级**/
    private int buildLevel;
    /**状态，0-未养殖，1-养殖中，2-可收获**/
    private int state;
    /**可养殖的幼崽列表，state为0时存在**/
    private List<WarehouseBabyInfo> babyList;
    /**最大人口数**/
    private int maxPopuplation;
    /**开始时间，state为1时存在**/
    private Date startDateTime;
    /**结束时间，state为1时存在**/
    private Date finishDateTime;
    /**喂养次数，state为1时存在**/
    private int feedCount;
    /**喂养最大次数，state为1时存在**/
    private int feedMaxCount;
    /**货物ID，state为2时存在**/
    private int goodsId;
    /**货物数量，state为2时存在**/
    private int count;

    public LivestockInfo() {
    }

    public LivestockInfo(int buildLevel, int state, List<WarehouseBabyInfo> babyList, int maxPopuplation, Date startDateTime, Date finishDateTime, int feedCount, int feedMaxCount, int goodsId, int count) {
        this.buildLevel = buildLevel;
        this.state = state;
        this.babyList = babyList;
        this.maxPopuplation = maxPopuplation;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.feedCount = feedCount;
        this.feedMaxCount = feedMaxCount;
        this.goodsId = goodsId;
        this.count = count;
    }

    public int getBuildLevel() {
        return buildLevel;
    }

    public void setBuildLevel(int buildLevel) {
        this.buildLevel = buildLevel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<WarehouseBabyInfo> getBabyList() {
        return babyList;
    }

    public void setBabyList(List<WarehouseBabyInfo> babyList) {
        this.babyList = babyList;
    }

    public int getMaxPopuplation() {
        return maxPopuplation;
    }

    public void setMaxPopuplation(int maxPopuplation) {
        this.maxPopuplation = maxPopuplation;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Date finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public int getFeedCount() {
        return feedCount;
    }

    public void setFeedCount(int feedCount) {
        this.feedCount = feedCount;
    }

    public int getFeedMaxCount() {
        return feedMaxCount;
    }

    public void setFeedMaxCount(int feedMaxCount) {
        this.feedMaxCount = feedMaxCount;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
