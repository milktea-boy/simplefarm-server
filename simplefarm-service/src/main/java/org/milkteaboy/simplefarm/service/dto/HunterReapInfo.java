package org.milkteaboy.simplefarm.service.dto;

/**
 * 猎人小屋收获信息DTO
 */
public class HunterReapInfo {

    /**货物ID**/
    private int goodsId;
    /**货物数量**/
    private int count;

    public HunterReapInfo() {
    }

    public HunterReapInfo(int goodsId, int count) {
        this.goodsId = goodsId;
        this.count = count;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
