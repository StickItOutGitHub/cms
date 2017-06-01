package org.bread.worm.cms.channel.dao;

import java.util.List;

import org.bread.worm.cms.basic.dao.BaseDao;
import org.bread.worm.cms.channel.bean.Channel;
import org.bread.worm.cms.channel.bean.ChannelTree;

public interface ChannelDao extends BaseDao<Channel> {


	/**
	 * 根据父id获取所有的子栏目
	 * @param pid 父ID
	 * @return
	 */
	public List<Channel> listByParentId(Integer pid);
	

	/**
	 * 获取子栏目的最大的排序号
	 * @param pid  父ID
	 * @return
	 */
	public int getMaxOrderByParentId(Integer pid);
	
	/**
	 * 把所有的栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTree();
	/**
	 * 根据父类对象获取子类栏目，并且生成树列表
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> generateTreeByParent(Integer pid);
}
