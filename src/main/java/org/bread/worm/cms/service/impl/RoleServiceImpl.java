package org.bread.worm.cms.service.impl;

import java.util.List;

import org.bread.worm.cms.bean.CmsException;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.dao.RoleDao;
import org.bread.worm.cms.dao.UserDao;
import org.bread.worm.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;

	@Override
	public void add(Role role) {
		roleDao.add(role);

	}

	@Override
	public void delete(int id) {
		List<User> users = userDao.listRoleUsers(id);
		if (users != null && users.size() > 0) {
			throw new CmsException("该角色中存在用户，不能删除！");
		}
		roleDao.remove(id);
	}

	@Override
	public void update(Role role) {
		roleDao.modify(role);

	}

	@Override
	public Role load(int id) {
		return roleDao.load(id);
	}

	@Override
	public List<Role> listRole() {
		
		return roleDao.listRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		roleDao.deleteRoleUsers(rid);

	}

}
