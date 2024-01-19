package com.stein.anno;

public class AnnotationApp2 {
    public static void main(String[] args) throws NoSuchMethodException {
        a1();
    }

    public static void a1() throws NoSuchMethodException {
        // 通过反射读取注解信息
        MyAnnotation myAnnotation = MyClass.class.getMethod("myMethod").getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation.value());
        System.out.println(myAnnotation.count());
    }
}

class MyClass {
    @MyAnnotation(value = "myMethod", count = 100)
    public void myMethod() {

    }
}