package com.guhe.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Writing and Combining Comparators
 *
 * @author njl
 * @date 2023/2/18
 */
public class Lambda2App {
	public static void main(String[] args) {
//		l2();
//		l3();
//		l4();
//		l5();
//		l6();
		l7();
	}

	public static void l7() { // Dealing with Null Values
		Comparator<Integer> naturalOrder = Comparator.naturalOrder();
		// The nullsLast() and its sibling method nullsFirst() are factory methods of the Comparator interface.
		// Both take a comparator as an argument and do just that:
		// handle the null values for you, pushing them to the end, or putting them first in your sorted list.
		Comparator<Integer> naturalOrderNullsLast =
				Comparator.nullsLast(naturalOrder);
		List<Integer> list = Arrays.asList(10, 9, 8, 7, null, 5, null, 4, null);
		list.sort(naturalOrderNullsLast);
		System.out.println("list = " + list);
	}

	public static void l6() { // Reversing a Comparator
		Comparator<String> byLengthThenAlphabetically =
				Comparator.comparing(String::length)
						.thenComparing(Comparator.naturalOrder());
		List<String> strings = Arrays.asList("one", "two", "three", "four", "five");

		strings.sort(byLengthThenAlphabetically.reversed());
		System.out.println(strings);
	}

	public static void l5() { // Comparing Comparable Objects Using Their Natural Order
		// The Comparator API gives you a Comparator.naturalOrder() factory class. The comparator it
		// builds does exactly that: it compares any Comparable object using its compareTo() method.
		Comparator<String> byLengthThenAlphabetically =
				Comparator.comparing(String::length)
						.thenComparing(Comparator.naturalOrder());
		List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
		strings.sort(byLengthThenAlphabetically);
		System.out.println(strings);
	}

	public static void l4() { // Specialized Comparators
		// using these methods, you can chain the comparison with a comparator built on a specialized
		// function that returns a primitive type, without having any performance hit due to
		// boxing / unboxing.
		Comparator<User> ageComparator = Comparator.comparingInt(User::getAge);
		Comparator<User> ageThenSalaryComparator = ageComparator.thenComparingLong(User::getSalary);
		User user1 = new User("A", "B", 22, 12000L);
		User user2 = new User("A", "B", 22, 15000L);
		int i1 = ageThenSalaryComparator.compare(user1, user2);
		System.out.println("i1 = " + i1);
	}

	public static void l3() { // Chaining Comparators
		Comparator<User> byFirstName = Comparator.comparing(User::getFirstName);
		Comparator<User> byLastName = Comparator.comparing(User::getLastName);
		Comparator<User> byFirstNameThenLastName = byFirstName.thenComparing(byLastName);
		// or
//		Comparator<User> byFirstNameThenLastName =
//				Comparator.comparing(User::getFirstName)
//						.thenComparing(User::getLastName);
		User user1 = new User("Clay", "Wang");
		User user2 = new User("Clay", "Li");
		int compare = byFirstNameThenLastName.compare(user1, user2);
		System.out.println("compare = " + compare);
	}

	public static void l2() { // Using a Factory Method to Create a Comparator
		Function<String, Integer> toLength = String::length;
		Comparator<String> c1 = (s1, s2) ->
				Integer.compare(toLength.apply(s1), toLength.apply(s2));
		// or
		Comparator<String> c2 = Comparator.comparing(String::length);
		int i1 = c2.compare("abc", "abcd");
		System.out.println("i1 = " + i1);
		int i2 = c2.compare("apple", "app");
		System.out.println("i2 = " + i2);

		User user1 = new User("James", "Clay");
		User user2 = new User("Ellen", "Clay");
		Comparator<User> c3 = Comparator.comparing(User::getFirstName);
		int i3 = c3.compare(user1, user2);
		System.out.println("i3 = " + i3);
	}

	public static void l1() { // Implementing a Comparator with a Lambda Expression
		// 创建一个比较 Integer 整数的 Comparator
//		Comparator<Integer> c1 = (n1, n2) -> n1.compareTo(n2);
		Comparator<Integer> c1 = Integer::compare;
	}
}

class User {
	private String firstName;
	private String lastName;
	private Integer age;
	private Long salary;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String lastName, Integer age, Long salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}
}
