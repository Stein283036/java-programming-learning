package com.guhe.reflect;

import java.io.Closeable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/2/1
 */
public class ReflectApp4 {
	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//		r1();
		r2();
	}

	/**
	 * 注解结合反射，如果类使用了 @Log 注解，那么给这个类中的方法执行前后打印日志，做功能增强
	 */
	public static void r2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Class<Op> cls = Op.class;
		Constructor<Op> constructor = cls.getConstructor(String.class, long.class);
		Op op = constructor.newInstance("任务1", 120L);
		Method[] methods = cls.getMethods();
		System.out.println("methods = " + Arrays.toString(methods));
		if (cls.isAnnotationPresent(Log.class)) {
			OpAdvance opAdvance = new OpAdvance();
			for (Method method : methods) {
				if (method.getDeclaringClass() == cls) {
					opAdvance.logBeforeOp();
					method.invoke(op);
					opAdvance.logAfterOp();
				}
			}
		}
	}

	public static void r1() {
		Class<String> cls = String.class;
		// 泛型上限，? 是 String的父类或String类
		// 获取类的父类对象
		Class<? super String> superclass = cls.getSuperclass();
		System.out.println("superclass = " + superclass);
		// 获取类实现的接口对象
		// getInterfaces()只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型
		Class<?>[] interfaces = cls.getInterfaces();
		for (Class<?> anInterface : interfaces) {
			System.out.println(anInterface.getName());
		}

		// 获取接口的父接口用 getInterfaces()
		System.out.println(Arrays.toString(Closeable.class.getInterfaces()));
		System.out.println(Closeable.class.getSuperclass());
	}
}

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Log {
	String name() default "";
}


@Log(name = "infoLog")
//@Log(name = "warnLog")
class Op {
	private String task;
	private long duration;

	public Op(String task, long duration) {
		this.task = task;
		this.duration = duration;
	}

	public void op1() {
		System.out.println("Op op1");
	}
}

class OpAdvance {
	public void logBeforeOp() {
		System.out.println("任务执行前 日志");
	}

	public void logAfterOp() {
		System.out.println("任务执行后 日志");
	}
}