package org.milkteaboy.simplefarm.service.dto;

/**
 * 畜舍信息DTO
 */
public class UserLivestockInfo {

    /**建筑ID**/
    private int buildId;
    /**幼崽ID**/
    private int id;
    /**幼崽数量**/
    private int count;

    public UserLivestockInfo() {
    }

    public UserLivestockInfo(int buildId, int id, int count) {
        this.buildId = buildId;
        this.id = id;
        this.count = count;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
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
