package org.bread.worm.cms.dao.impl;

import java.util.List;

import org.bread.worm.cms.basic.dao.impl.BaseDaoImpl;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.dao.RoleDao;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public List<Role> listRole() {
		String hql = "from Role";
		return this.listByHql(hql);
	}

	@Override
	public void deleteRoleUsers(int rid) {
		String hql = "delete UserRole ur where ur.role.id = ?";
		this.updateByHqlSingle(hql, rid);
	}

}
