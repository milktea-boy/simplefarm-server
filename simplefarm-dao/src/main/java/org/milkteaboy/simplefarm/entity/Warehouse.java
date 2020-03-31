package org.milkteaboy.simplefarm.entity;

/**
 * 仓库实体
 */
public class Warehouse {

    /**对象类型，，0-幼崽，1-种子，2-食物，3-货物**/
    private Integer objectType;
    /**对象ID**/
    private Integer objectId;
    /**数量**/
    private Integer count;
    /**用户**/
    private User user;

    public Warehouse() {
    }

    public Warehouse(Integer objectType, Integer objectId, Integer count, User user) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.count = count;
        this.user = user;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
