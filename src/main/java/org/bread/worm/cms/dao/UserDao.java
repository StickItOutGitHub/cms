package org.bread.worm.cms.dao;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.basic.dao.BaseDao;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.RoleType;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.bean.UserGroup;
import org.bread.worm.cms.bean.UserRole;

public interface UserDao extends BaseDao<User> {

	/**
	 * 通过用户id获取该用户拥有的角色
	 * @param userId 用户ID
	 * @return
	 */
	public List<Role> listUserRoles(int userId);
	
	
	/**
	 * 通过用户id获取该用户拥有的角色ID
	 * @param userId 用户ID
	 * @return
	 */
	public List<Integer> listUserRoleIds(int userId);
	
	
	/**
	 * 通过用户ID获取该用户所在组
	 * @param userId 用户ID
	 * @return
	 */
	public List<Group> listUserGroups(int userId);
	
	
	/**
	 * 通过用户ID获取该用户所在组ID
	 * @param userId 用户ID
	 * @return
	 */
	public List<Integer> listUserGroupIds(int userId);
	
	/**
	 * 通过用户ID和角色ID获取关联对象
	 * @param userId 用户ID
	 * @param roleId 角色ID
	 * @return
	 */
	public UserRole loadUserRole(int userId,int roleId);
	
	
	/**
	 * 根据用户和组获取用户组关联对象
	 * @param userId 用户ID
	 * @param groupId 用户组ID
	 * @return
	 */
	public UserGroup loadUserGroup(int userId,int groupId);
	
	/**
	 * 通过用户名查询用户对象
	 * @param username 用户名
	 * @return
	 */
	public User loadByUsername(String username);
	
	/**
	 * 通过角色ID获取该角色有哪些用户
	 * @param roleId 角色对象
	 * @return
	 */
	public List<User> listRoleUsers(int roleId);
	
	/**
	 * 通过用户类型获取该类型有哪些用户对象
	 * @param userType 用户类型
	 * @return
	 */
	public List<User> listRoleUsers(RoleType roleType);
	
	
	/**
	 * 通过用户组ID获取该组有哪些用户对象
	 * @param groupId 用户组ID
	 * @return
	 */
	public List<User> listGroupUsers(int groupId);
	
	/**
	 * 添加用户角色对象
	 * @param user
	 * @param roleId
	 */
	public void addUserRole(User user,Role role);
	/***
	 * 添加用户组对象
	 * @param user
	 * @param groupId
	 */
	public void addUserGroup(User user, Group group);
	
	/**
	 * 删除指定用户的角色信息
	 * @param userId
	 */
	public void removeUserRole(int userId);
	
	/**
	 * 删除指定用户的组信息
	 * @param userId
	 */
	public void removeUserGroup(int userId);
	
	
	/**
	 * 查询一组分页的用户对象
	 * @return
	 */
	public Pager<User> findUser();

	/***
	 * 删除指定用户的指定角色 的角色信息
	 * @param userId
	 * @param roleId
	 */
	public void removeUserRole(int userId, int roleId);
	
	/**
	 * 删除指定用户的指定组 的组信息
	 * @param userId
	 * @param groupId
	 */
	public void removeUserGroup(int userId, int groupId);
	
	
	/**
	 * 删除用户对象
	 * @param user
	 */
	public void removeUser(User user);
	
	
	/**
	 * 删除指定用户组中的所有用户信息
	 * @param groupId
	 */
	public void removeGroupUser(int groupId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
