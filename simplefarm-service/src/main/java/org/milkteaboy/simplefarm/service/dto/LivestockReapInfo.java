package org.milkteaboy.simplefarm.service.dto;

/**
 * 畜舍收获信息DTO
 */
public class LivestockReapInfo {

    /**收获的货物ID**/
    private int id;
    /**收获的数量**/
    private int count;

    public LivestockReapInfo() {
    }

    public LivestockReapInfo(int id, int count) {
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
