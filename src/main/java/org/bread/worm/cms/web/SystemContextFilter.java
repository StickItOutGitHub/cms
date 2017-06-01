package org.bread.worm.cms.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.bread.worm.cms.basic.bean.SystemContext;


public class SystemContextFilter implements Filter {

	private int pageSize;
	
	public SystemContextFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Integer offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (NumberFormatException e) {}
		
		try {
			SystemContext.setOrder(request.getParameter("order"));
			SystemContext.setSort(request.getParameter("sort"));
			SystemContext.setPageOffset(offset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(request,response);
		}finally {
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		try {
			pageSize = Integer.parseInt(fConfig.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 15;
		}
	}
}
