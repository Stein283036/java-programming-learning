package com.guhe.generics;

import java.io.Serializable;

/**
 * @author njl
 * @date 2023/1/29
 */
public class Dog implements Serializable {
	private String name;
	private Integer age;
	private String brand;

	public Dog(String name, Integer age, String brand) {
		this.name = name;
		this.age = age;
		this.brand = brand;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"name='" + name + '\'' +
				", age=" + age +
				", brand='" + brand + '\'' +
				'}';
	}
}
