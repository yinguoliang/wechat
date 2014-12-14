package com.my.weichat.pub;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.my.weichat.domain.Beautician;

public class WebUtils {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static<T> T generateObjectFromRequest(HttpServletRequest request,Class<T> cls) throws Exception{
		Field[] fields = cls.getDeclaredFields();
		T t = cls.newInstance();
		for(Field field : fields){
			String value = request.getParameter(field.getName());
			System.out.println("GEN::::"+field.getName()+"="+value);
			if(value!=null){
				String type = field.getType().getName();
				field.setAccessible(true);
				if(type.equals("java.lang.String")){
					field.set(t, value);
				}else if(type.equals("int") || type.equals("java.lang.Integer")){
					field.set(t, Integer.parseInt(value));
				}else if(type.equals("long")||type.equals("java.lang.Long")){
					field.set(t, Long.parseLong(value));
				}else if(type.equals("double")||type.equals("java.lang.Double")){
					field.set(t, Double.parseDouble(value));
				}else if(type.equals("java.util.Date")){
					field.set(t, sdf.parse(value));
				}
			}
		}
		return t;
	}
	public static void main(String args[]) throws Exception{
		Field[] fields=Beautician.class.getDeclaredFields();
		for(Field f : fields){
			System.out.println(f.getType().getName());
		}
	}
}
