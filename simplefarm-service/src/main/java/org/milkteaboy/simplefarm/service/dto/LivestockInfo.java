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
    /**开始时间，state为1时存在**/
    private Date startDateTime;
    /**结束时间，state为1时存在**/
    private Date finishDateTime;
    /**喂养次数，state为1时存在**/
    private int feedCount;
    /**喂养最大次数，state为1时存在**/
    private int feedMaxCount;
    /**货物列表，state为2时存在**/
    private List<WarehouseGoodsInfo> goodsList;

    public LivestockInfo() {
    }

    public LivestockInfo(int buildLevel, int state, List<WarehouseBabyInfo> babyList, Date startDateTime, Date finishDateTime, int feedCount, int feedMaxCount, List<WarehouseGoodsInfo> goodsList) {
        this.buildLevel = buildLevel;
        this.state = state;
        this.babyList = babyList;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.feedCount = feedCount;
        this.feedMaxCount = feedMaxCount;
        this.goodsList = goodsList;
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

    public List<WarehouseGoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<WarehouseGoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }
}
