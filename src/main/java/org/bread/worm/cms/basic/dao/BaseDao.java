package org.bread.worm.cms.basic.dao;

/***
 * 
 * 公共的DAO处理对象，包含了hibernate的所有基本操作和对SQL的操作
 * @author Long Tanglin
 * @since 2017-5-21 16:21:23
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 保存一个对象
	 * @param t 保存的对象
	 * @return  保存的对象
	 */
	public T add(T t);
	
	/**
	 * 根据ID删除一个对象
	 * @param id ID
	 */
	public void remove(int id);
	
	/**
	 * 更新一个对象
	 * @param t 需要更新的对象
	 */
	public void modify(T t);
	
	/**
	 * 根据ID查询单个对象
	 * @param id ID
	 * @return 查询到的单个对象
	 */
	public T load(int id);
	
	/***
	 * 通过hql语句查询一组不分页的对象
	 * @param hql hql语句 
	 * @param args 条件参数
	 * @param alias 别名参数
	 * @return 一组不分页的对象
	 */
//	public List<T> listByHql(String hql,Object[] args,Map<String, Object> alias);
//	public List<T> listByAliasHql(String hql,Map<String, Object> alias);
//	public List<T> listByHql(String hql,Object[] args);
//	public List<T> listByHqlSingle(String hql,Object args);
//	public List<T> listByHql(String hql);
	
	
	/**
	 * 通过hql语句查询一组分页的对象
	 * @param hql hql语句 
	 * @param args 条件参数
	 * @param alias 别名参数
	 * @return 一组分页的对象
	 */
//	public Pager<T> findByHql(String hql,Object[] args,Map<String, Object> alias);
//	public Pager<T> findByAliasHql(String hql,Map<String, Object> alias);
//	public Pager<T> findByHql(String hql,Object[] args);
//	public Pager<T> findByHqlSingle(String hql,Object args);
//	public Pager<T> findByHql(String hql);
	
	
	/**
	 * 通过hql查询单个对象
	 * @param hql hql语句 
	 * @param args 条件参数
	 * @return 单个对象
	 */ 
//	public Object queryObject(String hql,Object[] args);
//	public Object queryObjectSingle(String hql,Object args);
//	public Object queryObject(String hql);
//	public Object queryObject(String hql,Object[] args,Map<String, Object> alias);
//	public Object queryObjectByAlias(String hql,Object args,Map<String, Object> alias);
//	public Object queryObjectByAlias(String hql,Map<String, Object> alias);
	
	
	


	/**
	 * 通过hql更新对象
	 * @param hql hql语句
	 * @param args 条件参数
	 */
//	public void updateByHql(String hql,Object[] args);
//	public void updateByHqlSingle(String hql,Object args);
//	public void updateByHql(String hql);
	
	
	/***
	 * 通过sql查询一组不分页的对象
	 * @param sql SQL 语句
	 * @param args 条件参数
	 * @param alias 别名
	 * @param clazz 实体类型
	 * @param hasEntity  是否是一个hibernate管理的对象，如果不是，则需要使用setResultTransform查询
	 * @return 一组不分页的对象
	 */
//	public <N extends Object> List<N> listByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> List<N> listBySql(String sql,Object[] args,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> List<N> listBySql(String sql,Object args,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> List<N> listBySql(String sql,Map<String, Object> alias,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> List<N> listBySql(String sql,Class<?> clazz,boolean hasEntity);
//	
	
	

	/***
	 * 通过sql查询一组分页的对象
	 * @param sql SQL 语句
	 * @param args 条件参数
	 * @param alias 别名
	 * @param clazz 实体类型
	 * @param hasEntity  是否是一个hibernate管理的对象，如果不是，则需要使用setResultTransform查询
	 * @return 一组分页的对象
	 */
//	public <N extends Object> Pager<N> findByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> Pager<N> findBySql(String sql,Object[] args,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> Pager<N> findBySqlSingle(String sql,Object args,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> Pager<N> findBySql(String sql,Class<?> clazz,boolean hasEntity);
//	public <N extends Object> Pager<N> findBySql(String sql,Map<String, Object> alias,Class<?> clazz,boolean hasEntity);
}
