package com.stein.anno;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/2/2
 */
@PropertySource("name1")
@PropertySource("name2")
@PropertySource("name3")
public class AnnotationApp1 {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
//		a1();
//		a2();
//		a3();
        validateBean(new MyBean("", 20));
    }

    public void a2(@RequestParam(path = "/users") @ApiParam(name = "用户 id") String userId) {
    }


    /**
     * 校验注解练习
     */
    public static void validateBean(MyBean myBean) throws IllegalAccessException {
        Class<? extends MyBean> cls = myBean.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RangeCheck.class)) {
                if (field.getType() == String.class) {
                    String prop = (String) field.get(myBean);
                    RangeCheck annotation = field.getAnnotation(RangeCheck.class);
                    if (prop.length() > annotation.max() || prop.length() < annotation.min()) {
                        throw new IllegalArgumentException(prop);
                    }
                }
            }
        }
        System.out.println("your bean is validated with no errors found");
    }

    /**
     * 读取方法上的注解
     */
    public static void a2() throws NoSuchMethodException {
        Class<AnnotationApp1> cls = AnnotationApp1.class;
        Method a2 = cls.getMethod("a2", String.class);
        Annotation[][] parameterAnnotations = a2.getParameterAnnotations();
        // 获取 a2 方法的第一个参数 userId 的所有注解
        Annotation[] userIdAnnotation = parameterAnnotations[0];
        System.out.println("userIdAnnotation = " + Arrays.toString(userIdAnnotation));
        for (Annotation annotation : userIdAnnotation) {
            if (annotation instanceof RequestParam) {
                RequestParam requestParam = (RequestParam) annotation;
                System.out.println(requestParam.path());
            } else if (annotation instanceof ApiParam) {
                ApiParam apiParam = (ApiParam) annotation;
                System.out.println(apiParam.name());
            }
        }
    }

    public static void a1() {
        Class<AnnotationApp1> cls = AnnotationApp1.class;
        PropertySources annotation = cls.getAnnotation(PropertySources.class);
        PropertySource[] value = annotation.value();
        for (PropertySource propertySource : value) {
            System.out.println(propertySource.value());
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface RequestParam {
    String path();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface ApiParam {
    String name();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface PropertySources {
    PropertySource[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PropertySources.class)
@Target(ElementType.TYPE)
@interface PropertySource {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface RangeCheck {
    int min() default 0;

    int max() default 255;
}

class MyBean {
    @RangeCheck(min = 5, max = 256)
    String name;
    Integer age;

    public MyBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
