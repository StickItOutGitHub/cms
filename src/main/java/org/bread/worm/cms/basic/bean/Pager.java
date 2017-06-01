package org.bread.worm.cms.basic.bean;

import java.util.List;

/**
 * 分页对象
 * @author Long Tanglin
 * @since 2017-5-21 15:47:09
 * @param <T>
 */
public class Pager<T> {

	/**
	 * 分页大小
	 */
	private int size;
	/**
	 * 分页的起始页
	 */
	private int offset;
	/**
	 * 总页数
	 */
	private Long total;
	/**
	 * 分页数据
	 */
	private List<T> datas;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
