package com.guhe.java8.lambda;

@FunctionalInterface // 函数式接口，有且仅有一个抽象方法
public interface Converter<F, T> {
	T convert(F from);
}