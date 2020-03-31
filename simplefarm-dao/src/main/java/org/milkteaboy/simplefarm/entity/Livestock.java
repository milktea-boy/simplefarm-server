package org.milkteaboy.simplefarm.entity;

/**
 * 畜舍配置实体
 */
public class Livestock {

    /**等级**/
    private Integer level;
    /**升级价格**/
    private Integer price;
    /**最大人口数**/
    private Integer maxPopulation;

    public Livestock() {
    }

    public Livestock(Integer level, Integer price, Integer maxPopulation) {
        this.level = level;
        this.price = price;
        this.maxPopulation = maxPopulation;
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

    public Integer getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(Integer maxPopulation) {
        this.maxPopulation = maxPopulation;
    }
}
