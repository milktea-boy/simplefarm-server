package org.milkteaboy.simplefarm.service.dto;

import java.util.List;

/**
 * 用户信息DTO
 */
public class UserInfo {

    /**农场等级**/
    private int level;
    /**金币数**/
    private int coin;
    /**建筑信息**/
    private List<UserBuildInfo> buildInfo;
    /**水井水滴数**/
    private Integer wellWaterCount;
    /**畜舍信息**/
    private List<UserLivestockInfo> livestockInfo;
    /**地块信息**/
    private List<UserGroundInfo> groundInfo;
    /**猎人小屋状态，0-未派出，1-已派出，2-可收获**/
    private Integer hunterState;

    public UserInfo() {
    }

    public UserInfo(int level, int coin, List<UserBuildInfo> buildInfo, Integer wellWaterCount, List<UserLivestockInfo> livestockInfo, List<UserGroundInfo> groundInfo, Integer hunterState) {
        this.level = level;
        this.coin = coin;
        this.buildInfo = buildInfo;
        this.wellWaterCount = wellWaterCount;
        this.livestockInfo = livestockInfo;
        this.groundInfo = groundInfo;
        this.hunterState = hunterState;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public List<UserBuildInfo> getBuildInfo() {
        return buildInfo;
    }

    public void setBuildInfo(List<UserBuildInfo> buildInfo) {
        this.buildInfo = buildInfo;
    }

    public Integer getWellWaterCount() {
        return wellWaterCount;
    }

    public void setWellWaterCount(Integer wellWaterCount) {
        this.wellWaterCount = wellWaterCount;
    }

    public List<UserLivestockInfo> getLivestockInfo() {
        return livestockInfo;
    }

    public void setLivestockInfo(List<UserLivestockInfo> livestockInfo) {
        this.livestockInfo = livestockInfo;
    }

    public List<UserGroundInfo> getGroundInfo() {
        return groundInfo;
    }

    public void setGroundInfo(List<UserGroundInfo> groundInfo) {
        this.groundInfo = groundInfo;
    }

    public Integer getHunterState() {
        return hunterState;
    }

    public void setHunterState(Integer hunterState) {
        this.hunterState = hunterState;
    }
}
