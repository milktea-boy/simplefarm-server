package org.milkteaboy.simplefarm.service.dto;

import java.util.Date;

/**
 * 猎人小屋信息DTO
 */
public class HunterInfo {

    /**建筑等级**/
    private int buildLevel;
    /**状态，0-未派出，1-已派出，2-可收获**/
    private int state;
    /**开始时间，当state为1时存在**/
    private Date startDateTime;
    /**结束时间，当state当1时存在**/
    private Date finishDateTime;
    /**货物ID**/
    private int goodsId;
    /**货物数量**/
    private int count;

    public HunterInfo() {
    }

    public HunterInfo(int buildLevel, int state, Date startDateTime, Date finishDateTime, int goodsId, int count) {
        this.buildLevel = buildLevel;
        this.state = state;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
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
