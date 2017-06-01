package org.bread.worm.cms.channel.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_channel")
public class Channel implements Serializable {
	private static final long serialVersionUID = -1858234888004447437L;

	/**
	 * 栏目的主键
	 */
	private int id;
	/**
	 * 栏目的名称
	 */
	private String name;
	/**
	 * 父类栏目
	 */
	private Channel parent;
	/**
	 * 栏目是否是自定义链接，0表示否，1表示是
	 */
	private int isCustomLink;
	/**
	 * 自定义链接的地址
	 */
	private String customLinkUrl;
	/**
	 * 栏目的类型，枚举类型，该枚举中存在一个name属性用来标识中文的名称
	 */
	private ChannelType type;
	/**
	 * 是否是首页栏目，0表示否，1表示是
	 */
	private int isIndex;
	/**
	 * 是否是首页的顶部导航栏目，0表示否，1表示是
	 */
	private int isTopNav;
	/**
	 * 是否是推荐栏目，0表示否，1表示是
	 */
	private int isRecommend;
	/**
	 * 栏目的状态，0表示启用，1表示停止
	 */
	private int status;
	/**
	 * 栏目的排序编号
	 */
	private int orders;

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

	@ManyToOne
	@JoinColumn(name="pid")
	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	public int getIsCustomLink() {
		return isCustomLink;
	}

	public void setIsCustomLink(int isCustomLink) {
		this.isCustomLink = isCustomLink;
	}

	public String getCustomLinkUrl() {
		return customLinkUrl;
	}

	public void setCustomLinkUrl(String customLinkUrl) {
		this.customLinkUrl = customLinkUrl;
	}

	public ChannelType getType() {
		return type;
	}

	public void setType(ChannelType type) {
		this.type = type;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	public int getIsTopNav() {
		return isTopNav;
	}

	public void setIsTopNav(int isTopNav) {
		this.isTopNav = isTopNav;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", parent=" + parent + ", isCustomLink=" + isCustomLink
				+ ", customLinkUrl=" + customLinkUrl + ", type=" + type + ", isIndex=" + isIndex + ", isTopNav="
				+ isTopNav + ", isRecommend=" + isRecommend + ", status=" + status + ", orders=" + orders + "]";
	}

}
