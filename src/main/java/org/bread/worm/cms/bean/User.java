package org.bread.worm.cms.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


/**
 * 用户实体
 * @author Long Tanglin
 * @since 2017-5-22 16:57:19
 * 
 */
@Entity
@Table(name="t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 8211940977031687141L;
	private int id;
	
	/**
	 * 用户登录名
	 */
	private String username;
	
	/**
	 * 用户中文名称
	 */
	private String nickname;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 用户邮件
	 */
	private String email;
	
	/**
	 * 用户电话号码
	 */
	private String phone;
	
	/**
	 * 用户状态 1：启用 0：停用
	 */
	private int status;
	
	/**
	 * 用户的创建时间
	 */
	private Date createDate;

	
	
	
	public User() {
	}

	public User(int id, String username, String password, String nickname, String email, String phone, int status) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message="用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@NotNull(message="用户密码不能为空")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	@Email(message="邮件格式不正确")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
