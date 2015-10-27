/**
 * POINT_INFO 表 POJO
 */
package com.honoka.entity;

public class POINT {
    // 记录 ID
    private Integer recordId;
    // 索引关键字
    private String keyId;
    // 百度记录纬度
    private double baiduRecordLng;
    // 百度记录经度
    private double baiduRecordLat;
    // 高德记录纬度
    private double amapRecordLng;
    // 高德记录经度
    private double amapRecordLat;
    // 删除标记
    private Integer deleteFlag;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public double getBaiduRecordLng() {
        return baiduRecordLng;
    }

    public void setBaiduRecordLng(double baiduRecordLng) {
        this.baiduRecordLng = baiduRecordLng;
    }

    public double getBaiduRecordLat() {
        return baiduRecordLat;
    }

    public void setBaiduRecordLat(double baiduRecordLat) {
        this.baiduRecordLat = baiduRecordLat;
    }

    public double getAmapRecordLng() {
        return amapRecordLng;
    }

    public void setAmapRecordLng(double amapRecordLng) {
        this.amapRecordLng = amapRecordLng;
    }

    public double getAmapRecordLat() {
        return amapRecordLat;
    }

    public void setAmapRecordLat(double amapRecordLat) {
        this.amapRecordLat = amapRecordLat;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
