package org.bread.worm.cms.basic.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.basic.bean.SystemContext;
import org.bread.worm.cms.basic.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO 实现
 * 
 * @author Long Tanglin
 * @since 2017-5-21 17:27:33
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clazz;

	public Class<?> getClazz() {
		if (clazz == null) {
			// 获取泛型的Class对象
			clazz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}
		return clazz;
	}

	/**
	 * 获取session对象
	 * 
	 * @return
	 */
	protected Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public T add(T t) {
		getSession().save(t);
		return t;
	}

	public void remove(int id) {
		getSession().delete(this.load(id));

	}

	public void modify(T t) {
		getSession().update(t);
	}

	public T load(int id) {
		T t = (T) getSession().get(getClazz(), id);
		return t;

	}

	/***
	 * 设置排序
	 * 
	 * @param hql
	 * @return
	 */
	private String initSort(String hql) {
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();

		if (sort != null && !"".equals(sort)) {
			hql += " order by " + sort;
			if (!"desc".equals(order))
				hql += " asc";
			else
				hql += " desc";
		}
		return hql;
	}

	/**
	 * 设置别名条件参数
	 * 
	 * @param query
	 * @param alias
	 */
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					query.setParameterList(key, (Collection) val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	/**
	 * 设置条件参数
	 * 
	 * @param query
	 * @param args
	 */
	private void setParameter(Query query, Object[] args) {
		if (args != null && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	public List<T> listByHql(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	public List<T> listByAliasHql(String hql, Map<String, Object> alias) {
		return this.listByHql(hql, null, alias);
	}

	public List<T> listByHql(String hql, Object[] args) {
		return this.listByHql(hql, args, null);
	}

	public List<T> listByHqlSingle(String hql, Object args) {
		return this.listByHql(hql, new Object[] { args });
	}

	public List<T> listByHql(String hql) {
		return this.listByHql(hql, null);
	}

	/**
	 * 分页总数
	 * 
	 * @param hql
	 * @param isHql
	 * @return
	 */
	private String getCountHql(String hql, boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String chql = "select count(1) " + e;
		if (isHql)
			chql = chql.replaceAll("fetch", "");
		return chql;

	}

	/**
	 * 设置分页参数
	 * 
	 * @param query
	 * @param pager
	 */
	private void setPagers(Query query, Pager pager) {
		Integer pageOffset = SystemContext.getPageOffset();
		Integer pageSize = SystemContext.getPageSize();

		if (pageOffset == null || pageOffset < 0) {
			pageOffset = 0;
		}

		if (pageSize == null || pageSize == 0) {
			pageSize = 15;
		}

		pager.setSize(pageSize);
		pager.setOffset(pageOffset);

		query.setFirstResult(pageOffset);
		query.setMaxResults(pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tanglin.basic.dao.IBaseDao#find(java.lang.String,
	 * java.lang.Object[], java.util.Map)
	 */

	public Pager<T> findByHql(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		String chql = getCountHql(hql, true);

		Query query = getSession().createQuery(hql);
		Query cquery = getSession().createQuery(chql);

		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);

		setParameter(query, args);
		setParameter(cquery, args);

		Pager pages = new Pager();
		setPagers(query, pages);

		List<T> datas = query.list();
		pages.setDatas(datas);

		Long total = (Long) cquery.uniqueResult();
		pages.setTotal(total);

		return pages;
	}

	public Pager<T> findByAliasHql(String hql, Map<String, Object> alias) {
		return this.findByHql(hql, null, alias);
	}

	public Pager<T> findByHql(String hql, Object[] args) {
		return this.findByHql(hql, args, null);
	}

	public Pager<T> findByHqlSingle(String hql, Object args) {
		return this.findByHql(hql, new Object[] { args });
	}

	public Pager<T> findByHql(String hql) {
		return this.findByHql(hql, null);
	}

	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args, null);
	}

	public Object queryObjectSingle(String hql, Object args) {
		return this.queryObject(hql, new Object[] { args });
	}

	public Object queryObject(String hql) {
		return this.queryObject(hql, null);
	}

	public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectByAlias(String hql, Object args, Map<String, Object> alias) {
		return this.queryObject(hql, new Object[] { args }, alias);
	}

	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObjectByAlias(hql, null, alias);
	}

	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	public void updateByHqlSingle(String hql, Object args) {
		this.updateByHql(hql, new Object[] { args });
	}

	public void updateByHql(String hql) {
		this.queryObject(hql, null);
	}

	public <N extends Object> List<N> listByAliasSql(String sql, Object[] args, Map<String, Object> alias,
			Class<?> clazz, boolean hasEntity) {
		sql = initSort(sql);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		setAliasParameter(sqlQuery, alias);
		setParameter(sqlQuery, args);

		if (hasEntity) {
			sqlQuery.addEntity(clazz);
		} else {
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		}

		return sqlQuery.list();
	}

	public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clazz, boolean hasEntity) {
		return this.listByAliasSql(sql, args, null, clazz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object args, Class<?> clazz, boolean hasEntity) {
		return this.listBySql(sql, new Object[] { args }, clazz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Map<String, Object> alias, Class<?> clazz,
			boolean hasEntity) {
		return this.listByAliasSql(sql, null, alias, clazz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Class<?> clazz, boolean hasEntity) {
		return this.listByAliasSql(sql, null, null, clazz, hasEntity);
	}

	public <N extends Object> Pager<N> findByAliasSql(String sql, Object[] args, Map<String, Object> alias,
			Class<?> clazz, boolean hasEntity) {
		sql = initSort(sql);
		String cquery = getCountHql(sql, false);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		SQLQuery cSqlQuery = getSession().createSQLQuery(cquery);
		setAliasParameter(sqlQuery, alias);
		setAliasParameter(cSqlQuery, alias);

		setParameter(sqlQuery, args);
		setParameter(cSqlQuery, args);

		Pager pagers = new Pager();
		setPagers(sqlQuery, pagers);
		if (hasEntity) {
			sqlQuery.addEntity(clazz);
		} else {
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		}
		List<Object> datas = sqlQuery.list();
		pagers.setDatas(datas);
		long total = ((BigInteger) cSqlQuery.uniqueResult()).longValue();
		pagers.setTotal(total);
		return pagers;
	}

	public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Class<?> clazz, boolean hasEntity) {
		return this.findByAliasSql(sql, args, null, clazz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySqlSingle(String sql, Object args, Class<?> clazz, boolean hasEntity) {
		return this.findBySql(sql, new Object[] { args }, clazz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Class<?> clazz, boolean hasEntity) {
		return this.findByAliasSql(sql, null, null, clazz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Map<String, Object> alias, Class<?> clazz,
			boolean hasEntity) {
		return this.findByAliasSql(sql, null, alias, clazz, hasEntity);
	}
}
