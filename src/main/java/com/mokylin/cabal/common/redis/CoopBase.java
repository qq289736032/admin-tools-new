package com.mokylin.cabal.common.redis;

public class CoopBase {
	private String coopname;		//平台名
	private String key;				//key
	private String rechargeKey; 	//充值key
	private String baseUrl;			//官网Url
	private String errorUrl;		//错误跳转Url
	private String payUrl;			//充值Url
	private String bbsUrl;			//论坛Url
	private String cmUrl;			//防沉迷Url
	private String microClientUrl;	//微端url
	private String suggestUrl;		//建议地址Url

	public String getCoopname() {
		return coopname;
	}

	public void setCoopname(String coopname) {
		this.coopname = coopname;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRechargeKey() {
		return rechargeKey;
	}

	public void setRechargeKey(String rechargeKey) {
		this.rechargeKey = rechargeKey;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getBbsUrl() {
		return bbsUrl;
	}

	public void setBbsUrl(String bbsUrl) {
		this.bbsUrl = bbsUrl;
	}

	public String getCmUrl() {
		return cmUrl;
	}

	public void setCmUrl(String cmUrl) {
		this.cmUrl = cmUrl;
	}

	public String getMicroClientUrl() {
		return microClientUrl;
	}

	public void setMicroClientUrl(String microClientUrl) {
		this.microClientUrl = microClientUrl;
	}

	public String getSuggestUrl() {
		return suggestUrl;
	}

	public void setSuggestUrl(String suggestUrl) {
		this.suggestUrl = suggestUrl;
	}

}
