package org.bread.worm.cms.service;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.User;

public interface UserService {
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * @param user 用户对象
	 * @param rids 用户的所有角色信息
	 * @param gids 用户的所有组信息
	 */
	public void add(User user,Integer[]rids,Integer[]gids);
	
	/**
	 * 添加用户角色对象
	 * @param user
	 * @param roleId
	 */
	public void addUserRole(User user,Role role);
	
	/**
	 * 添加用户组对象
	 * @param user
	 * @param group
	 */
	public void addUserGroup(User user,Group group);
	
	
	/**
	 * 删除用户，注意需要把用户和角色和组的对应关系删除
	 * 如果用户存在相应的文章不能删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
	 * 对于group而已同样要做这个操作
	 * @param user
	 * @param rids
	 * @param gids
	 */
	public void update(User user,Integer[] rids,Integer[] gids);
	/**
	 * 更新用户的状态
	 * @param id
	 */
	public void updateStatus(int id);
	/**
	 * 列表用户
	 */
	public Pager<User> findUser();
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User load(int id);
	/**
	 * 获取用户的所有角色信息
	 * @param userId
	 * @return
	 */
	public List<Role> listUserRoles(int userId);
	/**
	 * 获取用户的所有组信息
	 * @param userId
	 * @return
	 */
	public List<Group> listUserGroups(int userId);
	
	/**
	 * 获取用户的所有角色信息
	 * @param userId
	 * @return
	 */
	public List<Integer> listUserRoleIds(int userId);
	
	/**
	 * 获取用户的所有组id
	 * @param userId
	 * @return
	 */
	public List<Integer> listUserGroupIds(int userId);
	
	/***
	 * 通过用户组ID获取该组有哪些用户
	 * @param gid
	 * @return
	 */
	public List<User> listGroupUsers(int gid);
	/**
	 * 通过角色ID获取该角色用哪些用户
	 * @param rid
	 * @return
	 */
	public List<User> listRoleUsers(int rid);
	
	
	/***
	 * 判断用户名是否已经存在
	 * @param username
	 * @return
	 */
	public boolean existsByUsername(String username);
	
	
	/**
	 * 删除指定用户组中的用户信息
	 * @param groupId 用户组ID
	 */
	public void removeGroupUser(int groupId);
	
	
	
}
