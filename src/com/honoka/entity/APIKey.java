package com.honoka.entity;

import java.sql.Date;

public class APIKey {

	private Integer recordId;

	private String key;

	private String provider;

	private Integer amount;

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
