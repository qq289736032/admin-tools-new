package com.mokylin.cabal.modules.tools.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mokylin.cabal.common.redis.Server;

public class Followed {

	private Server target;
	
	private List<Server> followedList =new ArrayList<>();
	
	private String followedAreaNames;

	public Server getTarget() {
		return target;
	}

	public void setTarget(Server target) {
		this.target = target;
	}

	public List<Server> getFollowedList() {
		return followedList;
	}

	public void setFollowedList(List<Server> followedList) {
		this.followedList = followedList;
	}

	public Followed(Server target, List<Server> followedList) {
		super();
		this.target = target;
		this.followedList = followedList;
	}

	public Followed() {
		super();
	}

	public Followed(Server target) {
		super();
		this.target = target;
	}
	
	public void addFollowed(Server server) {
		this.followedList.add(server);
	}

	public String getFollowedAreaNames() {
		String tmp = "";
		Collections.sort(followedList,new Comparator<Server>(){
            public int compare(Server arg0, Server arg1) {
                return arg0.getId()-arg1.getId();
            }
        });
		if(followedList.size() >0 &&followedList!=null){
			 for(int i=0;i<followedList.size();i++){
				 if(i==0){
			      tmp=followedList.get(i).getName();
				 }else{
				      tmp=tmp+","+followedList.get(i).getName();
				 }
			 }
			}
		return tmp;
	}

	public void setFollowedAreaNames(String followedAreaNames) {
		
		this.followedAreaNames = followedAreaNames;
	}
	
	
}
