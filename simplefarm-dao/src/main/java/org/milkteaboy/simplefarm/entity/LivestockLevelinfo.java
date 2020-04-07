package org.milkteaboy.simplefarm.entity;

/**
 * 畜舍等级信息实体
 */
public class LivestockLevelinfo {

    /**等级**/
    private Integer level;
    /**最大人口**/
    private Integer maxPopulation;

    public LivestockLevelinfo() {
    }

    public LivestockLevelinfo(Integer level, Integer maxPopulation) {
        this.level = level;
        this.maxPopulation = maxPopulation;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(Integer maxPopulation) {
        this.maxPopulation = maxPopulation;
    }
}
