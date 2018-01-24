package com.mokylin.cabal.modules.tools.entity;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.persistence.IdEntity;
import com.mokylin.cabal.common.redis.Server;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


/**
 * 作者: 稻草鸟人 日期: 2014/10/20 15:22 项目: cabal-tools
 */
@Entity
@Table(name = "game_platform")
@DynamicInsert
@DynamicUpdate
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GamePlatform extends IdEntity<GamePlatform> {

	private static final long serialVersionUID = -5205623595790789389L;

	private String name;

	private String description;

	private String signKey;

	private String pid;

	private List<Server> gameServerList = Lists.newArrayList(); // 游戏服务列表

	public GamePlatform() {
		super();
	}

	public GamePlatform(String id) {
		super();
		this.id = id;
	}

	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min = 1, max = 100)
	public String getSignKey() {
		return signKey;
	}

	@Length(min = 1, max = 100)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	@Transient
	public List<Server> getGameServerList() {
		return gameServerList;
	}

	public void setGameServerList(List<Server> gameServerList) {
		this.gameServerList = gameServerList;
	}

	

	// @OneToMany(mappedBy = "gamePlatform", fetch=FetchType.EAGER)
	// @Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	// @OrderBy(value="id") @Fetch(FetchMode.SUBSELECT)
	// @NotFound(action = NotFoundAction.IGNORE)
	// //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	// public List<GameServer> getGameServerList() {
	// return gameServerList;
	// }
	//
	// public void setGameServerList(List<GameServer> gameServerList) {
	// this.gameServerList = gameServerList;
	// }

}
