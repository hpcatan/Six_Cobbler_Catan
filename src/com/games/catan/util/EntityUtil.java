package com.games.catan.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.games.catan.exception.CatanLogicException;
import com.sun.xml.internal.ws.util.UtilException;

public class EntityUtil {
	/**
	 * get GetMethodName by fieldName.
	 * @param fieldName
	 * @return
	 */
	public static String getGetMethodNameFromFieldName(String fieldName){
		String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		return getMethodName;
	}
	/**
	 * set GetMethodName by fieldName.
	 * @param fieldName
	 * @return
	 */
	public static String getSetMethodNameFromFieldName(String fieldName){
		String setMethodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		return setMethodName;
	}
	/**
	 * validate all fields in entity by use regular expression. 
	 * @param entity
	 * @param regularExpression
	 * @return return null if validate success. return a failed message if validate failed.
	 * @throws UtilException 
	 */
	public static String getMessageByValidateEntityStringField(Object entity,String regularExpression) throws CatanLogicException{
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			try {
				Class<? extends Object> fieldClass = field.getType();
				if(fieldClass!=String.class){
					continue;
				}
				
				String methodName = getGetMethodNameFromFieldName(field.getName());
				Method method = clazz.getDeclaredMethod(methodName);
				Object resObject = method.invoke(entity);
				if(resObject==null)
					continue;
				String fieldValue = (String)resObject;
				if(!fieldValue.matches(regularExpression)){
					return "Validate entity field value failed. "+field.getName()+"="+fieldValue+" is invalid. ";
				}
			} catch (IllegalArgumentException e) {
				throw new UtilException("exception when entity get a field value, entity="+entity+" ,field name="+field.getName()+". "+e.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new UtilException("exception when entity get a field value, entity="+entity+" ,field name="+field.getName()+". "+e.getMessage(), e);
			} catch (SecurityException e) {
				throw new UtilException("exception when entity get a field value, entity="+entity+" ,field name="+field.getName()+". "+e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new UtilException("exception when entity get a field value, entity="+entity+" ,field name="+field.getName()+". "+e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new UtilException("exception when entity get a field value, entity="+entity+" ,field name="+field.getName()+". "+e.getMessage(), e);
			}
		}
		return null;
	}
}
