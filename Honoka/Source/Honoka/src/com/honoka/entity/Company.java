/**
 * COM_INFO 表 POJO
 */
package com.honoka.entity;

public class Company {
    // 记录 ID
    private Integer recordId;
    // 公司 ID
    private String comId;
    // 公司名字
    private String comName;
    // 公司位置
    private String comAddr;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComAddr() {
        return comAddr;
    }

    public void setComAddr(String comAddr) {
        this.comAddr = comAddr;
    }

}
