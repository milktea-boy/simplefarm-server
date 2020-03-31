package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户地块实体
 */
public class UserGround {

    /**地块序号**/
    private Integer index;
    /**浇水次数**/
    private Integer waterCount;
    /**播种时间**/
    private Date sowDatetime;
    /**用户**/
    private User user;
    /**种子**/
    private Seed seed;

    public UserGround() {
    }

    public UserGround(Integer index, Integer waterCount, Date sowDatetime, User user, Seed seed) {
        this.index = index;
        this.waterCount = waterCount;
        this.sowDatetime = sowDatetime;
        this.user = user;
        this.seed = seed;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }
}
