package com.guhe.java8.annotations;

import java.lang.annotation.*;

/**
 * 可重复注解 Java 8 中新出现了一个 @Repeatable 注解
 *
 * @author njl
 * @date 2023/2/13
 */
public class Annotations1App {
	public static void main(String[] args) {
		// 使用可重复注解的时候编译器在幕后会将可重复注解转换为老式的容器形式注解即 @Hints({@Hint("hint1"), @Hint("hint2")})
		// 因此如果想用反射获得使用这种方式的注解时就要注意了
		Hint h1 = Person2.class.getAnnotation(Hint.class);
		System.out.println("h1 = " + h1); // null
		Hints h2 = Person2.class.getAnnotation(Hints.class);
		Hint[] hints = h2.value();
		for (Hint hint : hints) {
			String value = hint.value();
			System.out.println("value = " + value);
		}
		Hint[] hints2 = Person2.class.getAnnotationsByType(Hint.class);
		System.out.println(hints2.length);
	}
}

@Hint("hint1") // 使用可重复注解 java 8 以后
@Hint("hint2") // 使用可重复注解
class Person2 {

}

@Hints({@Hint("hint1"), @Hint("hint2")}) // 使用容器注解 java 8 以前
class Person1 {

}

@Repeatable(Hints.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Hint {
	String value();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Hints {
	Hint[] value();
}