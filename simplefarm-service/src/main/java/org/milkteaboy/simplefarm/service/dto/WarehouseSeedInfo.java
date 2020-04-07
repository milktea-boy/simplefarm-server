package org.milkteaboy.simplefarm.service.dto;

/**
 * warehouse模块种子DTO
 */
public class WarehouseSeedInfo {

    /**种子ID**/
    private int id;
    /**种子数量**/
    private int count;

    public WarehouseSeedInfo() {
    }

    public WarehouseSeedInfo(int id, int count) {
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
