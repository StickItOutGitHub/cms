package org.bread.worm.cms.channel.bean;

/***
 * 栏目类型枚举
 * @author Long Tanglin
 * @since 2017-5-29 23:01:41
 */
public enum ChannelType {

	NAV_CHANNEL("导航栏目"),
	TOPIC_LIST("文章列表栏目"),
	TOPIC_CONTEXT("文章内容栏目"),
	TOPIC_IMG("图片列表栏目");
	
	private String chineseName;

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	private ChannelType(String chineseName) {
		this.chineseName = chineseName;
	}
	
}
