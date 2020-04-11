package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户水井信息实体
 */
public class UserWell {

    /**用户ID**/
    private Integer userId;
    /**上次收获时间**/
    private Date reapDatetime;

    public UserWell() {
    }

    public UserWell(Integer userId, Date reapDatetime) {
        this.userId = userId;
        this.reapDatetime = reapDatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReapDatetime() {
        return reapDatetime;
    }

    public void setReapDatetime(Date reapDatetime) {
        this.reapDatetime = reapDatetime;
    }
}
