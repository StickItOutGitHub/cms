package org.bread.worm.cms.dao.impl;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.basic.dao.impl.BaseDaoImpl;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.RoleType;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.bean.UserGroup;
import org.bread.worm.cms.bean.UserRole;
import org.bread.worm.cms.dao.UserDao;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public List<Role> listUserRoles(int userId) {
		String hql = "select ur.role from UserRole ur where ur.user.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	public List<Integer> listUserRoleIds(int userId) {
		String hql = "select role.id from UserRole ur where ur.user.id = ?";
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	public List<Group> listUserGroups(int userId) {
		String hql = "select group from UserGroup ug where ug.user.id = ?";
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	public List<Integer> listUserGroupIds(int userId) {
		String hql = "select group.id from UserGroup ug where ug.user.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	public UserRole loadUserRole(int userId, int roleId) {
		String hql = "from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id = ? and r.id = ?";
		return (UserRole)queryObject(hql, new Object[]{userId,roleId});
//		return (UserRole)getSession().createQuery(hql).setParameter(0, userId).setParameter(1, roleId).uniqueResult();
	}

	public UserGroup loadUserGroup(int userId, int groupId) {
		String hql = "from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id = ? and g.id = ?";
		return (UserGroup)queryObject(hql, new Object[]{userId,groupId});
//		return (UserGroup)getSession().createQuery(hql).setParameter(0, userId).setParameter(1, groupId).uniqueResult();
	}

	public User loadByUsername(String username) {
		String hql = "from User where username = ?";
		return (User)queryObjectSingle(hql, username);
//		return (User)getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}

	public List<User> listRoleUsers(int roleId) {
		String hql = "select user from UserRole ur where ur.role.id = ?";
		
		return listByHqlSingle(hql, roleId);
//		return getSession().createQuery(hql).setParameter(0, roleId).list();
	}

	public List<User> listRoleUsers(RoleType roleType) {
		String hql = "select user from UserRole ur where ur.role.roleType = ?";
		return listByHqlSingle(hql, roleType);
//		return getSession().createQuery(hql).setParameter(0, roleType).list();
	}

	public List<User> listGroupUsers(int groupId) {
		String hql = "select user from UserGroup ug where ug.group.id = ?";
		return listByHqlSingle(hql, groupId);
//		return getSession().createQuery(hql).setParameter(0, groupId).list();
	}

	@Override
	public void addUserRole(User user, Role role) {
		UserRole userRole = this.loadUserRole(user.getId(), role.getId());
		if (userRole != null)return;
		userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		getSession().save(userRole);
	}

	@Override
	public void addUserGroup(User user, Group group) {
		UserGroup userGroup = this.loadUserGroup(user.getId(), group.getId());
		if (userGroup != null)return;
		userGroup = new UserGroup();
		userGroup.setUser(user);
		userGroup.setGroup(group);
		getSession().save(userGroup);
		
	}

	@Override
	public void removeUserRole(int userId) {
		String hql = "delete UserRole ur where ur.user.id = ?";
		updateByHqlSingle(hql, userId);
	}

	@Override
	public void removeUserGroup(int userId) {
		String hql = "delete UserGroup ug where ug.user.id = ?";
		updateByHqlSingle(hql, userId);
	}

	@Override
	public Pager<User> findUser() {
		String hql = "from User";
		return this.findByHql(hql);
	}

	@Override
	public void removeUserRole(int userId, int roleId) {
		String hql = "delete UserRole ur where ur.user.id = ? and ur.role.id = ?";
		updateByHql(hql, new Object[]{userId,roleId});
	}

	@Override
	public void removeUserGroup(int userId, int groupId) {
		String hql = "delete UserGroup ug where ug.user.id = ? and ug.group.id = ?";
		updateByHql(hql, new Object[]{userId,groupId});
	}

	@Override
	public void removeUser(User user) {
		String hql = "delete User where id = ?";
		updateByHqlSingle(hql, user.getId());
	}

	@Override
	public void removeGroupUser(int groupId) {
		String hql = "delete UserGroup ug where ug.group.id = ?";
		updateByHqlSingle(hql, groupId);
	}
	
}
