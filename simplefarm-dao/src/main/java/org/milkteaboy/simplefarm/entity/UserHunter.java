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

    public UserHunter() {
    }

    public UserHunter(Integer userId, Date sendDatetime) {
        this.userId = userId;
        this.sendDatetime = sendDatetime;
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
}
