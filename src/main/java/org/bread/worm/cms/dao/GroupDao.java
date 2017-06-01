package org.bread.worm.cms.dao;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.basic.dao.BaseDao;
import org.bread.worm.cms.bean.Group;

public interface GroupDao extends BaseDao<Group> {

	/**
	 * 查询所有的用户组对象 不分页
	 * @return
	 */
	public List<Group> listGroup();
	
	/**
	 * 查询一组分页的用户组对象
	 * @return
	 */
	public Pager<Group> findGroup();
	/***
	 * 删除指定用户组的用户信息
	 * @param gid
	 */
	public void deleteGroupUsers(int gid);
}
