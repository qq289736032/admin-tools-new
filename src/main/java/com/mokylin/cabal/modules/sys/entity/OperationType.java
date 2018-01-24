package com.mokylin.cabal.modules.sys.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class OperationType extends MybatisBaseBean {

	private static final long serialVersionUID = 3464405967351538244L;
	
	public OperationType(String chDes,String enDes){
		this.chDes = chDes;
		this.enDes= enDes;
	}
	
	private String chDes;    //中文描述
	private String enDes;    //英文描述
	
	public String getChDes() {
		return chDes;
	}
	public void setChDes(String chDes) {
		this.chDes = chDes;
	}
	public String getEnDes() {
		return enDes;
	}
	public void setEnDes(String enDes) {
		this.enDes = enDes;
	}
	
	
	
	
}
