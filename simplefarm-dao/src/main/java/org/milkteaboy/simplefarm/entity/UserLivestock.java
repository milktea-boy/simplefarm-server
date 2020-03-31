package org.milkteaboy.simplefarm.entity;

import java.util.Date;

/**
 * 用户畜舍实体
 */
public class UserLivestock {

    /**幼崽数量**/
    private Integer count;
    /**当前喂养次数**/
    private Integer feedCount;
    /**养殖时间**/
    private Date breedDatetime;
    /**用户**/
    private User user;
    /**建筑**/
    private Build build;
    /**幼崽**/
    private Baby baby;

    public UserLivestock() {
    }

    public UserLivestock(Integer count, Integer feedCount, Date breedDatetime, User user, Build build, Baby baby) {
        this.count = count;
        this.feedCount = feedCount;
        this.breedDatetime = breedDatetime;
        this.user = user;
        this.build = build;
        this.baby = baby;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFeedCount() {
        return feedCount;
    }

    public void setFeedCount(Integer feedCount) {
        this.feedCount = feedCount;
    }

    public Date getBreedDatetime() {
        return breedDatetime;
    }

    public void setBreedDatetime(Date breedDatetime) {
        this.breedDatetime = breedDatetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }
}
