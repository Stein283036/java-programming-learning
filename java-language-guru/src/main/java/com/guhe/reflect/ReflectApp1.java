package com.guhe.reflect;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/2/1
 */
@MyAnno
public class ReflectApp1 implements Serializable {
	public static void main(String[] args) {
//		reflect1();
//		reflect2();
//		printClsInfo(ReflectApp1.class);
		System.out.println(StaticInner.class.isMemberClass());
		System.out.println(new Runnable() {
			@Override
			public void run() {

			}
		}.getClass().isAnonymousClass());
		int[] arr = {1, 2, 3};
		System.out.println(arr.getClass().isArray());
	}

	private static class StaticInner {

	}

	private static void printClsInfo(Class<?> cls) {
		System.out.println(cls.getName());
		System.out.println(cls.getTypeName());
		System.out.println(cls.getSimpleName());
		AnnotatedType[] annotatedInterfaces = cls.getAnnotatedInterfaces();
		System.out.println(Arrays.toString(annotatedInterfaces));
		Class<?> superclass = cls.getSuperclass();
		Annotation[] annotations = cls.getAnnotations();
		System.out.println("annotations = " + Arrays.toString(annotations));
		System.out.println("superclass = " + superclass);
		System.out.println(cls.getPackage().getName());
		System.out.println(cls.isAnnotation());
		System.out.println(cls.isAnnotationPresent(MyAnno.class));
		System.out.println(cls.isArray());
		System.out.println(cls.isInterface());
	}

	/**
	 * instanceof 和 getClass() 的比较
	 */
	public static void reflect2() {
		Integer integer = new Integer(123);
		boolean b1 = integer instanceof Integer; // true
		boolean b2 = integer instanceof Number;
		boolean b3 = integer.getClass() == Integer.class; // true
//		boolean b4 = integer.getClass() == Number.class; // false
	}

	/**
	 * 获取 Class 对象的方式
	 */
	public static void reflect1() {
		Class<Integer> cls1 = Integer.class;
		Class<? extends String> cls2 = "HelloWorld".getClass();
		try {
			Class<?> cls3 = Class.forName("java.lang.Readable");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {

}