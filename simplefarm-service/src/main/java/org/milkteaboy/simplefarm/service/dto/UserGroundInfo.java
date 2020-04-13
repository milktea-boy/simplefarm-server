package org.milkteaboy.simplefarm.service.dto;

import java.util.List;

/**
 * 用户农地信息
 */
public class UserGroundInfo {

    /**建筑ID**/
    private int buildId;
    /**地块信息**/
    private List<UserGround> userGroundList;

    public UserGroundInfo() {
    }

    public UserGroundInfo(int buildId, List<UserGround> userGroundList) {
        this.buildId = buildId;
        this.userGroundList = userGroundList;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public List<UserGround> getUserGroundList() {
        return userGroundList;
    }

    public void setUserGroundList(List<UserGround> userGroundList) {
        this.userGroundList = userGroundList;
    }
}
