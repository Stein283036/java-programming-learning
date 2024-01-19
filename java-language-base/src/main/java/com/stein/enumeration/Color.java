package com.stein.enumeration;

/**
 * @author njl
 * @date 2023/1/29
 */
public enum Color {
    RED, GREEN, BLUE;

    Color() {
        System.out.println("Construct enum constant: " + this.name());
    }

    public void colorInfo() {
        System.out.println("my color is " + this.name());
    }
}
