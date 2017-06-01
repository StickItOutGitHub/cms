package org.bread.worm.cms.dao;

import java.util.List;

import org.bread.worm.cms.basic.dao.BaseDao;
import org.bread.worm.cms.bean.Role;

public interface RoleDao extends BaseDao<Role> {
	
	/**
	 * 查询所有的角色对象
	 * @return
	 */
	public List<Role> listRole();

	/**
	 * 删除指定角色中的用户信息
	 * @param rid
	 */
	public void deleteRoleUsers(int rid);
}
