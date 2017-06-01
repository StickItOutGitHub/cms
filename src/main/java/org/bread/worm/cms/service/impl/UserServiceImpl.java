package org.bread.worm.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.bean.CmsException;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.dao.GroupDao;
import org.bread.worm.cms.dao.RoleDao;
import org.bread.worm.cms.dao.UserDao;
import org.bread.worm.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private GroupDao groupDao;
	

	/***
	 * 保存用户角色对象
	 * @param user
	 * @param roleId
	 */
	private void saveUserRole(User user,int roleId){
		Role role = roleDao.load(roleId);
		if(role == null) throw new CmsException("要添加的用户角色不存在");
		userDao.addUserRole(user, role);
	}
	
	private void saveUserGroup(User user,int groupId){
		Group group = groupDao.load(groupId);
		if(group == null) throw new CmsException("要添加用户的组对象不存在");
		userDao.addUserGroup(user,group);
	}
	
	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		User u = userDao.loadByUsername(user.getUsername());
		if (u != null){
			throw new CmsException("该用户已经存在，不能再添加");
		}
		user.setCreateDate(new Date());
		userDao.add(user);
		if (rids != null) {
			for (Integer rid : rids) {
				this.saveUserRole(user, rid);
			}

			if (gids != null) {
				for (Integer gid : gids) {
					this.saveUserGroup(user, gid);
				}
			}
		}

	}

	@Override
	public void addUserRole(User user, Role role) {
		if(role == null) throw new CmsException("要添加的用户角色不存在");
		userDao.addUserRole(user,role);
	}
	

	@Override
	public void addUserGroup(User user, Group group) {
		if(group == null) throw new CmsException("要添加用户的组对象不存在");
		userDao.addUserGroup(user,group);
		
	}

	@Override
	public void delete(int id) {
		// TODO 如果用户有发布的文章 则不能删除，只能停用
		
		//删除用户关联的组信息
		userDao.removeUserGroup(id);
		//删除用户关联的角色信息
		userDao.removeUserRole(id);
		//删除该用户对象
		userDao.removeUser(userDao.load(id));
		
	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		//该用户对象已经存在的角色ID
		List<Integer> erids = userDao.listUserRoleIds(user.getId());
		//该用户对象已经存在的组ID
		List<Integer> egids = userDao.listUserGroupIds(user.getId());

		
		// 如果 rids 角色ID中保存了该用户对象原本不存在的角色ID，则添加该用户的角色信息
		for (Integer erid : rids) {
			if (!erids.contains(erid)) {
				Role role = roleDao.load(erid);
				if (role == null) throw new CmsException("要添加的用户角色不存在");
				userDao.addUserRole(user, role);
			}
		}
		// 如果 gids 组ID中保存了该用户对象原本不存在的组ID，则添加该用户的组信息
		for (Integer gid : gids) {
			if (!egids.contains(gid)) {
				Group group = groupDao.load(gid);
				if (group == null) {
					 throw new CmsException("要添加用户的组对象不存在");
				}
				userDao.addUserGroup(user, group);
			}
		}
		
		
		// 如果该对象已经存在的角色ID中包含了rids不存在的ID，则删除该用户对象的角色信息
		for (Integer erid : erids) {
			if (!ArrayUtils.contains(rids, erid)) {
				userDao.removeUserRole(user.getId(),erid);
			}
		}

		// 如果该对象已经存在的组ID中包含了gids不存在的ID，则删除该用户对象的组信息
		for (Integer egid : egids) {
			if (!ArrayUtils.contains(gids, egid)) {
				userDao.removeUserGroup(user.getId(), egid);
			}
		}
	}

	@Override
	public void updateStatus(int id) {
		User user = userDao.load(id);
		if (user == null) throw new CmsException("需要修改的对象不存在");
		if (user.getStatus() == 0) 
			user.setStatus(1);
		else
			user.setStatus(0);
		userDao.modify(user);
		
	}

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<Role> listUserRoles(int userId) {
		return userDao.listUserRoles(userId);
	}

	@Override
	public List<Group> listUserGroups(int userId) {
		return userDao.listUserGroups(userId);
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {
		return userDao.listUserRoleIds(userId);
	}

	@Override
	public List<Integer> listUserGroupIds(int userId) {
		return userDao.listUserGroupIds(userId);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
		return userDao.listGroupUsers(gid);
	}

	@Override
	public List<User> listRoleUsers(int rid) {
		return userDao.listRoleUsers(rid);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userDao.loadByUsername(username) == null;
	}

	@Override
	public void removeGroupUser(int groupId) {
		userDao.removeGroupUser(groupId);
	}
}
