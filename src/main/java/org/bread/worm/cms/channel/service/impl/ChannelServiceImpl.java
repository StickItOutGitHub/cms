package org.bread.worm.cms.channel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bread.worm.cms.basic.dao.impl.BaseDaoImpl;
import org.bread.worm.cms.bean.CmsException;
import org.bread.worm.cms.channel.bean.Channel;
import org.bread.worm.cms.channel.bean.ChannelTree;
import org.bread.worm.cms.channel.dao.ChannelDao;
import org.bread.worm.cms.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("channelService")
public class ChannelServiceImpl extends BaseDaoImpl<Channel> implements ChannelService {

	@Autowired
	private ChannelDao channelDao;

	@Override
	public void add(Channel channel, Integer pid) {
		int max = channelDao.getMaxOrderByParentId(pid);
		if (pid != null) {
			Channel pc = null;
			if (pid == 0) {
				pc = new Channel();
				pc.setId(0);
				pc.setName("系统栏目管理");
				pc.setParent(null);
			}else {
				pc = channelDao.load(pid);
			}
			if (pc == null)
				throw new CmsException("指定的父栏目不存在，不能添加子栏目");
			else
				channel.setParent(pc);
			channel.setOrders(max + 1);
			channelDao.add(channel);
		}
	}

	@Override
	public void clearTopic(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Channel channel) {
		channelDao.modify(channel);

	}

	@Override
	public List<Channel> listByParentId(Integer pid) {
		return channelDao.listByParentId(pid);
	}

	@Override
	public void removeChannel(int id) {
		// 1、需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParentId(id);
		if (cs != null && cs.size() > 0)
			throw new CmsException("要删除的栏目还有子栏目，无法删除");
		// TODO 2、需要判断是否存在文章
		// TODO 3、需要删除和组的关联关系
		channelDao.remove(id);
	}

	@Override
	public Channel loadChannel(Integer id) {
		Channel channel = null;
		if (id != 0) {
			channel = channelDao.load(id);
		}else {
			channel = new Channel();
			channel.setId(id);
			channel.setName("系统栏目管理");
		}
		return channel;
	}

	@Override
	public List<ChannelTree> generateTree() {
		
		List<ChannelTree> trees = new ArrayList<ChannelTree>();
		trees.add(0, new ChannelTree(0, "系统栏目管理", -1));

		List<ChannelTree> tree = channelDao.generateTree();
		
		for (ChannelTree t : tree) {
			if(t.getPid() == null) t.setPid(0);
			trees.add(new ChannelTree(t.getId(),t.getName(),t.getPid()));
		}
		
		return trees;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

}
