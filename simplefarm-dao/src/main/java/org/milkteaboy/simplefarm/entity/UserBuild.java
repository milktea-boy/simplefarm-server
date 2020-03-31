package org.milkteaboy.simplefarm.entity;

/**
 * 用户建筑实体
 */
public class UserBuild {

    /**等级**/
    private Integer level;
    /**用户**/
    private User user;
    /**建筑**/
    private Build build;

    public UserBuild() {
    }

    public UserBuild(Integer level, User user, Build build) {
        this.level = level;
        this.user = user;
        this.build = build;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
}
