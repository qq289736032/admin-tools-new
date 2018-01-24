package com.mokylin.cabal.modules.chuangqu.entity;

import java.util.ArrayList;
import java.util.List;

public class ChuangquResponse {

	private int total;
	private int code;
	private String msg;
	private List<?> data;

	public ChuangquResponse(List<?> data) {
		this.total = data != null ? data.size() : 0;
		this.data = data;
		this.code = 0;
		this.msg = "success";
	}

	public ChuangquResponse() {
		this.total = 0;
		this.data = new ArrayList<>();
		this.code = 1;
		this.msg = "fail";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
