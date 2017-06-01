package org.bread.worm.cms.service.impl;

import java.util.List;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.bean.CmsException;
import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.dao.GroupDao;
import org.bread.worm.cms.dao.UserDao;
import org.bread.worm.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 
 * @author Long Tanglin
 * @since 2017-5-25 17:28:55
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private GroupDao groupDao;

	@Override
	public void add(Group group) {
		groupDao.add(group);
	}

	@Override
	public void delete(int gid) {

		List<User> groupUsers = userDao.listGroupUsers(gid);
		if (groupUsers != null && groupUsers.size() > 0) {
			throw new CmsException("该用户组存在用户，不能删除该用户组");
		}
		groupDao.remove(gid);

	}

	@Override
	public Group load(int id) {
		return groupDao.load(id);
	}

	@Override
	public void update(Group group) {
		groupDao.modify(group);

	}

	@Override
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	@Override
	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);

	}
}
