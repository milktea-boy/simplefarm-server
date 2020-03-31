package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户猎人小屋实体
 */
public class UserHunter {

    /**放出时间**/
    private Date sendDatetime;
    /**用户**/
    private User user;

    public UserHunter() {
    }

    public UserHunter(Date sendDatetime, User user) {
        this.sendDatetime = sendDatetime;
        this.user = user;
    }

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(Date sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
