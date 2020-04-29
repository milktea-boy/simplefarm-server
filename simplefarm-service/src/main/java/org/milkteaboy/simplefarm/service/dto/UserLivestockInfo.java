package org.milkteaboy.simplefarm.service.dto;

import java.util.Date;

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
    /**开始时间**/
    private Date startDateTime;
    /**结束时间**/
    private Date finishDateTime;

    public UserLivestockInfo() {
    }

    public UserLivestockInfo(int buildId, int id, int count, Date startDateTime, Date finishDateTime) {
        this.buildId = buildId;
        this.id = id;
        this.count = count;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Date finishDateTime) {
        this.finishDateTime = finishDateTime;
    }
}
