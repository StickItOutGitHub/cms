package org.bread.worm.cms.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组对象，使用该对象来获取可以发布文章的栏目信息
 * 可以理解为所在部门
 * @author  Long Tanglin
 * @since 2017-5-22 17:18:04
 */
@Entity
@Table(name="t_group")
public class Group implements Serializable {
	private static final long serialVersionUID = -3307677485492280015L;
	/**
	 * 组id
	 */
	private int id;
	/**
	 * 组名称
	 */
	private String name;
	/**
	 * 组描述信息
	 */
	private String descr;
	
	public Group() {
	}
	
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
