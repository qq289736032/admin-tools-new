/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.entity;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.persistence.IdEntity;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * 角色Entity
 * @author 稻草鸟人
 * @version 2014-05-15
 */
@Entity
@Table(name = "sys_role")
@DynamicInsert @DynamicUpdate
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity<Role> {
	
	private static final long serialVersionUID = 1L;
	private Office office;	// 归属机构
	private String name; 	// 角色名称
	private String dataScope; // 数据范围
    private String isGlobal; //是否是全平台权限 1-是，0-否 全平台权限此角色可以查询所有平台所有服务器

	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表
	private List<Office> officeList = Lists.newArrayList(); // 按明细设置数据范围
    private List<GamePlatform> gamePlatformList = Lists.newArrayList(); //游戏服务器列表
    private List<String> gamePlatformIds = Lists.newArrayList();

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	public static final String DATA_SCOPE_COMPANY = "3";
	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	public static final String DATA_SCOPE_OFFICE = "5";
	public static final String DATA_SCOPE_SELF = "8";
	public static final String DATA_SCOPE_CUSTOM = "9";
    //public static final String DATA_SCOPE_GAMESERVER = "10";
	
	public Role() {
		super();
		this.dataScope = DATA_SCOPE_CUSTOM;
	}

	public Role(String id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="office_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

    public String getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(String isGlobal) {
        this.isGlobal = isGlobal;
    }

    @ManyToMany(mappedBy = "roleList", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Transient
	public List<String> getUserIdList() {
		List<String> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId());
		}
		return nameIdList;
	}

	@Transient
	public String getUserIds() {
		List<String> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Transient
	public List<String> getMenuIdList() {
		List<String> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	@Transient
	public void setMenuIdList(List<String> menuIdList) {
		menuList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	@Transient
	public String getMenuIds() {
		List<String> nameIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			nameIdList.add(menu.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	@Transient
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			for (String menuId : ids) {
				Menu menu = new Menu();
				menu.setId(menuId);
				menuList.add(menu);
			}
		}
	}

    @Transient
    public List<String> getGamePlatformIds() {
        List<String> gamePlatformIds = Lists.newArrayList();
        for (GamePlatform gamePlatform : gamePlatformList) {
            gamePlatformIds.add(gamePlatform.getId());
        }
        return gamePlatformIds;
    }

    @Transient
    public void setGamePlatformIds(List<String> gamePlatformIds) {
        gamePlatformList = Lists.newArrayList();
        for (String gamePlatformId : gamePlatformIds) {
            GamePlatform gamePlatform = new GamePlatform();
            gamePlatform.setId(gamePlatformId);
            gamePlatformList.add(gamePlatform);
        }
    }



	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sys_role_office", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "office_id") })
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Office> getOfficeList() {
		return officeList;
	}


	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_game_platform", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "game_platform_id") })
    @Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
    @OrderBy("id") @Fetch(FetchMode.SUBSELECT)
    @NotFound(action = NotFoundAction.IGNORE)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<GamePlatform> getGamePlatformList() {
        return gamePlatformList;
    }

    public void setGamePlatformList(List<GamePlatform> gamePlatformList) {
        this.gamePlatformList = gamePlatformList;
    }


    @Transient
	public List<String> getOfficeIdList() {
		List<String> officeIdList = Lists.newArrayList();
		for (Office office : officeList) {
			officeIdList.add(office.getId());
		}
		return officeIdList;
	}

	@Transient
	public void setOfficeIdList(List<String> officeIdList) {
		officeList = Lists.newArrayList();
		for (String officeId : officeIdList) {
			Office office = new Office();
			office.setId(officeId);
			officeList.add(office);
		}
	}

	@Transient
	public String getOfficeIds() {
		List<String> nameIdList = Lists.newArrayList();
		for (Office office : officeList) {
			nameIdList.add(office.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	@Transient
	public void setOfficeIds(String officeIds) {
		officeList = Lists.newArrayList();
		if (officeIds != null){
			String[] ids = StringUtils.split(officeIds, ",");
			for (String officeId : ids) {
				Office office = new Office();
				office.setId(officeId);
				officeList.add(office);
			}
		}
	}
	
//	@ElementCollection
//	@CollectionTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id") })
//	@Column(name = "user_id")
//	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public List<Long> getUserIdList() {
//		return userIdList;
//	}
//
//	public void setUserIdList(List<Long> userIdList) {
//		this.userIdList = userIdList;
//	}
	
	/**
	 * 获取权限字符串列表
	 */
	@Transient
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}

	@Transient
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	@Transient
	public static boolean isAdmin(String id){
		return id != null && id.equals("1");
	}
	
//	@Transient
//	public String getMenuNames() {
//		List<String> menuNameList = Lists.newArrayList();
//		for (Menu menu : menuList) {
//			menuNameList.add(menu.getName());
//		}
//		return StringUtils.join(menuNameList, ",");
//	}

    @Transient
    public boolean hasAllPlatformPermission(){
        return this.isGlobal.equals("1");
    }
}
