package org.bread.worm.cms.service;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.bean.Group;

public interface GroupService {

	/***
	 * 添加组对象
	 * @param group
	 */
	public void add(Group group);

	/**
	 * 根据指定的ID删除组对象
	 * 如果该用户组存在用户，则不能删除该用户组
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 根据ID查询单个组对象
	 * @param id
	 * @return
	 */
	public Group load(int id);

	/**
	 * 更新单个组对象
	 * @param group
	 */
	public void update(Group group);

	/**
	 * 查询所有的组对象
	 * @return
	 */
	public List<Group> listGroup();

	/**
	 * 查询一组分页的组对象
	 * @return
	 */
	public Pager<Group> findGroup();

	/***
	 * 删除指定组ID的用户组信息
	 * @param gid
	 */
	public void deleteGroupUsers(int gid);
	
	

}
