package org.milkteaboy.simplefarm.entity;

/**
 * 仓库实体
 */
public class Warehouse {

    /**用户ID**/
    private Integer userId;
    /**对象类型，0-幼崽，1-种子，2-食物，3-货物**/
    private Integer objectType;
    /**对象ID**/
    private Integer objectId;
    /**对象**/
    private Object object;
    /**数量**/
    private Integer count;

    public Warehouse() {
    }

    public Warehouse(Integer userId, Integer objectType, Integer objectId, Object object, Integer count) {
        this.userId = userId;
        this.objectType = objectType;
        this.objectId = objectId;
        this.object = object;
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
