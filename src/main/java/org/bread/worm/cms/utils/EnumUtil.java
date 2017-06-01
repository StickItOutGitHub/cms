package org.bread.worm.cms.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.bread.worm.cms.channel.bean.ChannelType;


/**
 * 枚举处理工具类
 * @author Long Tanglin
 * 
 * @since 2017-5-29 21:02:25
 */
@SuppressWarnings("rawtypes")
public class EnumUtil {
	
	/***
	 * 枚举转  List<Integer>    获取枚举索引集合
	 * @param en
	 * @return
	 */
	public static List<Integer> enum2ListOrdinal(Class<? extends Enum> en){
		if (en.isEnum()) {
			List<Integer> ids = new ArrayList<Integer>();
			Enum[] enums =en.getEnumConstants();
			for (Enum e : enums) {
				ids.add(e.ordinal());
			}
			return ids;
		}
		return null;
	}
	
	
	/***
	 *  枚举转  List<String>   获取枚举名称集合
	 * @param en
	 * @return
	 */
	public static List<String> enum2ListName(Class<? extends Enum> en){
		if (en.isEnum()) {
			List<String> names = new ArrayList<String>();
			Enum[] enums =en.getEnumConstants();
			for (Enum e : enums) {
				names.add(e.name());
			}
			return names;
		}
		return null;
	}
	
	
	/***
	 * 枚举转  Map<Integer, String>  Map<ordinal,name>
	 * @param en
	 * @return
	 */
	public static Map<Integer, String> enum2Map(Class<? extends Enum> en){
		if (en.isEnum()) {
			Map<Integer, String> maps = new HashMap<Integer,String>();
			Enum[] enums =en.getEnumConstants();
			for (Enum e : enums) {
				maps.put(e.ordinal(), e.name());
			}
			return maps;
		}
		return null;
	}
	
	/**
	 * 将枚举中的值的某个属性转换为字符串列表
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public List<String> enumProp2List(Class<? extends Enum> clazz, String propertyName) {
		if (clazz.isEnum()) {
			Enum[] enums = clazz.getEnumConstants();
			List<String> result = new ArrayList<String>();
			for (Enum en : enums) {
				try {
					result.add(PropertyUtils.getProperty(en, propertyName).toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		return null;
	}
	
	/***
	 * 将枚举中的值的某个属性转换为序号和字符串列表
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public Map<Integer, String> enumProp2OrdinalMap(Class<? extends Enum> clazz, String propertyName) {
		if (clazz.isEnum()) {
			Enum[] enums = clazz.getEnumConstants();
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (Enum en : enums) {
				try {
					map.put(en.ordinal(), PropertyUtils.getProperty(en, propertyName).toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			return map;
		}
		return null;
	}
	
	
	public static Map<String,String> enumProp2NameMap(Class<? extends Enum> clz,String propName) {
		if(!clz.isEnum()) return null;
		try {
			Enum[] enums = clz.getEnumConstants();
			Map<String,String> rels = new HashMap<String,String>();
			for(Enum en:enums) {
				rels.put(en.name(),(String)PropertyUtils.getProperty(en, propName));
			}
			return rels;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(enumProp2NameMap(ChannelType.class, "chineseName"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
