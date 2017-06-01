package org.bread.worm.cms.dao.impl;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.basic.dao.impl.BaseDaoImpl;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.dao.GroupDao;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

	@Override
	public List<Group> listGroup() {
		String hql = "from Group";
		return this.listByHql(hql);
	}

	@Override
	public Pager<Group> findGroup() {
		String hql = "from Group";
		return this.findByHql(hql);
	}

	@Override
	public void deleteGroupUsers(int gid) {
		String hql = "delete UserGroup ug where ug.group.id = ?";
		updateByHqlSingle(hql, gid);
		
	}

}
