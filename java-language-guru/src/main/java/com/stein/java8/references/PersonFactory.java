package com.stein.java8.references;

/**
 * @author njl
 * @date 2023/2/13
 */
@FunctionalInterface
public interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}
