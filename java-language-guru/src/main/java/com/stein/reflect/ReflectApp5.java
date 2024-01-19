package com.stein.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author njl
 * @date 2023/2/2
 */
public class ReflectApp5 {
	public static void main(String[] args) {
		r1();
	}

	/**
	 * 动态代理
	 */
	public static void r1() {
		MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(
				MyInterface.class.getClassLoader(),
				new Class[]{MyInterface.class},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName().contains("toUpperList")) {
							System.out.println(method);
							List<String> upperList = new ArrayList<>();
							System.out.println();
							List<String> list = (ArrayList<String>) args[0];
							for (String s : list) {
								upperList.add(s.toUpperCase());
							}
							return upperList;
						} else if (method.getName().contains("sum")) {
							return (int) args[0] + (int) args[1];
						}
						return null;
					}
				}
		);

		List<String> upperList = myInterface.toUpperList(new ArrayList<>(Arrays.asList("apple", "banana", "orange", "watermelon")));
		System.out.println("upperList = " + upperList);

		int sum = myInterface.sum(10, 20);
		System.out.println("sum = " + sum);
	}
}

interface MyInterface {
	List<String> toUpperList(List<String> lowerList);

	int sum(int num1, int num2);
}
