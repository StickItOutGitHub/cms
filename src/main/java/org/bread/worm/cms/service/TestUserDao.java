package org.bread.worm.cms.service;

import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.dao.RoleDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestUserDao {

	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:*.xml");
		
		RoleDao dao = context.getBean(RoleDao.class);
		
		Role role = dao.load(2);
		
		System.out.println(role.getId());
		
	}
}
