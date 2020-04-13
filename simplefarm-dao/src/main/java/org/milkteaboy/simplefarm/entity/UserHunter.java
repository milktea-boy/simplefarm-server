package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户猎人小屋信息
 */
public class UserHunter {

    /**用户ID**/
    private Integer userId;
    /**放出时间**/
    private Date sendDatetime;
    /**货物数量**/
    private Integer goodsCount;

    public UserHunter() {
    }

    public UserHunter(Integer userId, Date sendDatetime, Integer goodsCount) {
        this.userId = userId;
        this.sendDatetime = sendDatetime;
        this.goodsCount = goodsCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(Date sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
