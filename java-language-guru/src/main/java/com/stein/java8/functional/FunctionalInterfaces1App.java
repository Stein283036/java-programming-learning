package com.stein.java8.functional;

import com.stein.java8.references.Person;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author njl
 * @date 2023/2/13
 */
@SuppressWarnings("ALL")
public class FunctionalInterfaces1App {
	public static void main(String[] args) {
//		f1();
//		f2();
//		f3();
//		f4();
//		f5();
		f6();
	}

	public static void f6() { // Optionals
		// Optionals are not functional interfaces, but nifty utilities to prevent NullPointerException.
		Optional<String> o1 = Optional.of("Optional");
		System.out.println(o1.isPresent()); // Return true if there is a value present, otherwise false.
		o1.ifPresent(s -> System.out.println(s.charAt(0)));

		Optional<Object> o2 = Optional.ofNullable(null);
		Object o3 = o2.orElse("Else"); // Return the value if present, otherwise return other.
		System.out.println("o3 = " + o3);
	}

	public static void f5() { // Comparators
		Comparator<Person> comparator = ((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		System.out.println(comparator.compare(p1, p2));
		System.out.println(comparator.reversed().compare(p1, p2));
	}

	public static void f4() { // Consumers
		// Consumers represent operations to be performed on a single input argument.
		Consumer<Person> greeter = person -> System.out.println(person.getFirstName());
		greeter.accept(new Person("Leo", "Anderson"));
	}

	public static void f3() { // Suppliers
		// Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
		Supplier<Person> personSupplier = Person::new;
		Person person = personSupplier.get();
	}

	public static void f2() { // Functions
		// Functions accept one argument and produce a result.
		// Default methods can be used to chain multiple functions together (compose, andThen).
		Function<String, Integer> toInteger = Integer::valueOf;
		Integer i1 = toInteger.apply("1234");
		System.out.println("i1 = " + i1);
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
	}

	public static void f1() { // Predicate
		Predicate<String> predicate = str -> str.length() > 0;
		boolean b1 = predicate.test("Hello World");
		System.out.println("b1 = " + b1);
		boolean b2 = predicate.negate().test("foo");
		System.out.println("b2 = " + b2);

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
	}
}
