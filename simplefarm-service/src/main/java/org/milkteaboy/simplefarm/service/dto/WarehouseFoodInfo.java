package org.milkteaboy.simplefarm.service.dto;

/**
 * warehouse模块食物DTO
 */
public class WarehouseFoodInfo {

    /**食物ID**/
    private int id;
    /**食物数量**/
    private int count;

    public WarehouseFoodInfo() {
    }

    public WarehouseFoodInfo(int id, int count) {
        this.id = id;
        this.count = count;
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
}
