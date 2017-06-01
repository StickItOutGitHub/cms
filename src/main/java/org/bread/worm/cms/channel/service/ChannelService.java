package org.bread.worm.cms.channel.service;

import java.util.List;

import org.bread.worm.cms.basic.dao.BaseDao;
import org.bread.worm.cms.channel.bean.Channel;
import org.bread.worm.cms.channel.bean.ChannelTree;

public interface ChannelService extends BaseDao<Channel> {

	/**
	 * 在指定的父节点下添加栏目
	 * @param channel 添加栏目
	 * @param pid 父ID
	 */
	public void add(Channel channel, Integer pid);

	/**
	 * 删除栏目对象
	 */
	public void removeChannel(int id);

	/**
	 * 清除指定栏目下的文章
	 * @param id
	 */
	public void clearTopic(int id);

	/**
	 * 修改栏目
	 * @param channel
	 */
	public void update(Channel channel);

	/**
	 * 获取单个栏目对象
	 */
	public Channel loadChannel(Integer id);

	/**
	 * 获取指定栏目下的子栏目列表
	 * 该方法首先检查SystemContext中是否存在排序，如果没有，则存在把orders作为排序字段
	 * @param pid
	 * @return
	 */
	public List<Channel> listByParentId(Integer pid);

	
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
