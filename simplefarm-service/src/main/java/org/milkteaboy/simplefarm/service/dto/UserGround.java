package org.milkteaboy.simplefarm.service.dto;

import java.util.Date;

/**
 * 用户地块DTO
 */
public class UserGround {

    /**地块序号**/
    private int index;
    /**种子ID**/
    private int id;
    /**开始种植时间**/
    private Date startDateTime;
    /**结束种植时间**/
    private Date finishDateTime;

    public UserGround() {
    }

    public UserGround(int index, int id, Date startDateTime, Date finishDateTime) {
        this.index = index;
        this.id = id;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
