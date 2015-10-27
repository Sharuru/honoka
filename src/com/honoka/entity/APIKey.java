/**
 * API_KEY 表 POJO
 */
package com.honoka.entity;

import java.sql.Date;

public class APIKey {

    // 记录 ID
    private Integer recordId;
    // API KEY
    private String key;
    // 提供者
    private String provider;
    // 使用次数
    private Integer amount;
    // 上一次使用时间
    private Date lastCall;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getLastCall() {
        return lastCall;
    }

    public void setLastCall(Date lastCall) {
        this.lastCall = lastCall;
    }

}
