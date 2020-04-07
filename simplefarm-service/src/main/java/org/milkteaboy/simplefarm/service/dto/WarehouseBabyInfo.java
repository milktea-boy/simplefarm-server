package org.milkteaboy.simplefarm.service.dto;

/**
 * warehouse模块幼崽DTO
 */
public class WarehouseBabyInfo {

    /**幼崽ID**/
    private int id;
    /**幼崽数量**/
    private int count;
    /**幼崽所占人口**/
    private int population;

    public WarehouseBabyInfo() {
    }

    public WarehouseBabyInfo(int id, int count, int population) {
        this.id = id;
        this.count = count;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
