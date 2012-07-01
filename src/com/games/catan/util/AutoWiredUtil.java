package com.games.catan.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


/**
 * 
 * @author chenlan
 *
 */
public class AutoWiredUtil {
	private String[] fieldClassPackageNameArray;
	private Collection<Object> objectCollection = new HashSet<Object>();
	public AutoWiredUtil(){}
	/**
	 * once the field's name of the object(to be wired) contains parameter fieldNameContainString,
	 * this field will be wired.
	 * @param fieldNameContainString
	 */
	public AutoWiredUtil(String... fieldClassPackageArray){
		this.fieldClassPackageNameArray = fieldClassPackageArray;
	}
	/**
	 * autowire the fields in sourceObject, the same operations to  the fields.
	 * @param sourceObject
	 * @return
	 * @throws UtilException 
	 */
	public void autowiredFieldsInObject(Object sourceObject) throws UtilException{
		if(sourceObject == null)
			return ;
		
		Class<? extends Object> clazz = sourceObject.getClass();
		Field[] fieldArray = clazz.getDeclaredFields();
		for(int i=0;i<fieldArray.length;i++){
			Field field = fieldArray[i];
			Class<?> fieldClass = field.getType();
			if(isClassPackageNameInFieldClassPackageNameArray(fieldClass)){
				String getMethodName = EntityUtil.getGetMethodNameFromFieldName(field.getName());
				String setMethodName = EntityUtil.getSetMethodNameFromFieldName(field.getName());
				try {
					Method getMethod = clazz.getDeclaredMethod(getMethodName);
					Object fieldValue = getMethod.invoke(sourceObject);
					if(fieldValue==null){
						Object object = this.getWiredObject(fieldClass);
						Method setMethod = clazz.getDeclaredMethod(setMethodName, fieldClass);
						setMethod.invoke(sourceObject, object);
					}
				} catch (Exception e) {
					throw new UtilException("class="+clazz.getName()+" method="+getMethodName+".", e);
				}
			}
		}
	}
	
	private Object getWiredObject(Class<?> clazz) throws InstantiationException, IllegalAccessException, UtilException{
		Iterator<Object> objectIterator = this.objectCollection.iterator();
		while(objectIterator.hasNext()){
			Object object = objectIterator.next();
			if(object.getClass().equals(clazz)){
				return object;
			}
		}
		Object object = clazz.newInstance();
		this.objectCollection.add(object);
		this.autowiredFieldsInObject(object);
		return object;
	}
	
	private boolean isClassPackageNameInFieldClassPackageNameArray(Class<?> clazz){
		String packageName = clazz.getPackage().getName();
		for(int i=0;i<this.fieldClassPackageNameArray.length;i++){
			String fieldClassPackageName = this.fieldClassPackageNameArray[i];
			if(packageName.equals(fieldClassPackageName)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(AutoWiredUtil.class.getPackage().getName());
	}
}
