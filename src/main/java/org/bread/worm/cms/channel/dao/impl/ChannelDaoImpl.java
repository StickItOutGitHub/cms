package org.bread.worm.cms.channel.dao.impl;

import java.util.List;

import org.bread.worm.cms.basic.dao.impl.BaseDaoImpl;
import org.bread.worm.cms.channel.bean.Channel;
import org.bread.worm.cms.channel.bean.ChannelTree;
import org.bread.worm.cms.channel.dao.ChannelDao;
import org.springframework.stereotype.Repository;

@Repository("channelDao")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao {


	@Override
	public List<Channel> listByParentId(Integer pid) {
		String sql = "select c from Channel c left join fetch c.parent cp where 1 = 1";
		if (pid == null || pid == 0) {
			sql += " and cp.id is null";
		}else{
			sql += " and cp.id = " + pid;
		}
		return listByHql(sql);
	}

	@Override
	public int getMaxOrderByParentId(Integer pid) {
		String sql = "select max(c.orders) from Channel c where 1 = 1";
		if (pid != null) {
			sql += " and c.parent.id = " + pid;
		}else{
			sql += " and c.parent.id is null";
		}
		Object object = queryObject(sql);
		if(object == null) return 0;
		return (Integer)object;
	}

	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel order by orders";
		return listBySql(sql, ChannelTree.class, false);
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		String sql = "select id,name,pid from t_channel where 1 = 1";
		
		if (pid == null) {
			sql += " and pid is null";
		}else {
			sql += " and pid = " + pid;
		}
		
		sql += " order by orders";
		return listBySql(sql, ChannelTree.class, false);
	}

}
