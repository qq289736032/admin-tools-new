package com.mokylin.cabal.common.redis;

public class BattleSubBean {
	private int type; // 1创建国家 2:创建战区
	private int id;	//创建国家的时候就是国家ID，创建战区的时候就是战区ID
	private Object obj;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
