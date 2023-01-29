package com.guhe.serialization;

import java.io.*;

/**
 * @author njl
 * @date 2023/1/29
 */
public class SerializationApp {
	public static void main(String[] args) {
//		serialization1();
		serialization2();
	}

	/**
	 * 使用 ObjectInputStream 反序列化对象
	 */
	public static void serialization2() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\serialization\\employee.ser"))) {
			Employee employee = (Employee) ois.readObject();
			System.out.println("employee = " + employee);
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用 ObjectOutputStream 序列化对象
	 */
	public static void serialization1() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\serialization\\employee.ser"))) {
			Address address = new Address();
			address.province = "江苏省";
			address.detail = "明华家园";
			Employee employee = new Employee();
			employee.name = "京龙";
			employee.SSN = 283036;
			employee.number = "17710450421";
			employee.address = address;
			oos.writeObject(employee);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Employee implements java.io.Serializable {
	public String name;
	public transient int SSN; // transient 修饰的字段不会参与序列化过程，在反序列化的时候会被赋予该类型的默认值。
	public String number;
	public Address address; // 对于引用类型需要被序列化的话那么也需要实现序列化接口，否则序列化和反序列时都会抛出异常，导致序列化过程失败。

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", SSN=" + SSN +
				", number='" + number + '\'' +
				", address=" + address +
				'}';
	}
}

class Address implements Serializable {
	public String province;
	public String detail;

	@Override
	public String toString() {
		return "Address{" +
				"province='" + province + '\'' +
				", detail='" + detail + '\'' +
				'}';
	}
}