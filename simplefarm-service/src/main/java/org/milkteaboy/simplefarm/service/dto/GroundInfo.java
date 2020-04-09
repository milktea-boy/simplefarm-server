package org.milkteaboy.simplefarm.service.dto;

import java.util.Date;
import java.util.List;

/**
 * 地块信息DTO
 */
public class GroundInfo {

    /**状态，0-未种植，1-种植中，2-可收获**/
    private int state;
    /**可种植种子列表，当state未0时存在**/
    private List<WarehouseSeedInfo> seedList;
    /**开始种植时间**/
    private Date startDateTime;
    /**完成种植时间**/
    private Date finishDateTime;
    /**当前浇水次数**/
    private int waterCount;
    /**最大浇水次数**/
    private int waterMaxCount;
    /**货物ID**/
    private int goodsId;
    /**货物数量**/
    private int count;

    public GroundInfo() {
    }

    public GroundInfo(int state, List<WarehouseSeedInfo> seedList, Date startDateTime, Date finishDateTime, int waterCount, int waterMaxCount, int goodsId, int count) {
        this.state = state;
        this.seedList = seedList;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.waterCount = waterCount;
        this.waterMaxCount = waterMaxCount;
        this.goodsId = goodsId;
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<WarehouseSeedInfo> getSeedList() {
        return seedList;
    }

    public void setSeedList(List<WarehouseSeedInfo> seedList) {
        this.seedList = seedList;
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

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    public int getWaterMaxCount() {
        return waterMaxCount;
    }

    public void setWaterMaxCount(int waterMaxCount) {
        this.waterMaxCount = waterMaxCount;
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
