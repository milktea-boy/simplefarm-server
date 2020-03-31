package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户水井实体
 */
public class UserWell {

    /**水滴数**/
    private Integer count;
    /**上次收获时间**/
    private Date reapDatetime;
    /**用户ID**/
    private User user;

    public UserWell() {
    }

    public UserWell(Integer count, Date reapDatetime, User user) {
        this.count = count;
        this.reapDatetime = reapDatetime;
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getReapDatetime() {
        return reapDatetime;
    }

    public void setReapDatetime(Date reapDatetime) {
        this.reapDatetime = reapDatetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
