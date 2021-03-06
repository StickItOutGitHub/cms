package org.bread.worm.cms.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色对象，用来对应可以访问的功能，系统中为了简单值定义了管理员，发布人员和审核人员
 * @author Long Tanglin
 * @since 2017-5-22 17:16:24
 */
@Entity
@Table(name="t_role")
public class Role implements Serializable {
	
	private static final long serialVersionUID = -3003543197201033477L;
	/**
	 * 角色id
	 */
	private int id;
	/**
	 * 角色的名称，中文
	 */
	private String name;
	/**
	 * 角色的编号，枚举类型
	 */
	private RoleType roleType;
	
	public Role() {
	}
	
	
	
	public Role(int id, String name, RoleType roleType) {
		this.id = id;
		this.name = name;
		this.roleType = roleType;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="role_type")
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	
}
