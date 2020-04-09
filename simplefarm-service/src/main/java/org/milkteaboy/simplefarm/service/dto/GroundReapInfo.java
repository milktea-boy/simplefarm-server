package org.milkteaboy.simplefarm.service.dto;

/**
 * 地块收获信息DTO
 */
public class GroundReapInfo {

    /**货物ID**/
    private int id;
    /**货物数量**/
    private int count;

    public GroundReapInfo() {
    }

    public GroundReapInfo(int id, int count) {
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
