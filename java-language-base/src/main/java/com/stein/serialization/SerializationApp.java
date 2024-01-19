package com.stein.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author njl
 * @date 2023/1/29
 */
public class SerializationApp {
    public static void main(String[] args) {
//		serialization1();
        deserialization();
    }

    /**
     * 使用 ObjectInputStream 反序列化对象
     */
    public static void deserialization() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("D:\\java-learning\\01-JavaBasic\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\stein\\serialization\\employee.ser")))) {
            Employee employee = (Employee) ois.readObject();
            System.out.println("employee = " + employee);
            employee.mailCheck();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 ObjectOutputStream 序列化对象
     */
    public static void serialization() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("D:\\java-learning\\01-JavaBasic\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\stein\\serialization\\employee.ser")))) {
            Address address = new Address();
            address.province = "江苏省";
            address.detail = "雨花台区";

            Employee employee = new Employee();
            employee.setName("倪京龙");
            employee.setSSN(283036);
            employee.setNumber("17710450421");
            employee.setAddress(address);

            oos.writeObject(employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

