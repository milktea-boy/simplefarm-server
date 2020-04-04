package org.milkteaboy.simplefarm.entity;

/**
 * 建筑等级信息实体
 */
public class BuildLevelinfo {

    /**建筑ID**/
    private Integer id;
    /**建筑等级**/
    private Integer level;
    /**价格**/
    private Integer price;

    public BuildLevelinfo() {
    }

    public BuildLevelinfo(Integer id, Integer level, Integer price) {
        this.id = id;
        this.level = level;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
