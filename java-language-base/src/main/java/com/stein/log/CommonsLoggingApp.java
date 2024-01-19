package com.stein.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author njl
 * @date 2023/1/29
 */
public class CommonsLoggingApp {
    private static final Log LOGGER = LogFactory.getLog(CommonsLoggingApp.class);

    // 子类可以继承 log，且 getClass 调用的是子类的方法
    protected final Log log = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        // class org.apache.commons.logging.impl.Jdk14Logger
        // 底层使用的是 Java 的 Logger
        // 加入了 log4j 依赖后使用的是 class org.apache.logging.log4j.jcl.Log4jLog
        System.out.println(LOGGER.getClass());
        LOGGER.info("this is an info message");
        LOGGER.error("this is an error message");
    }
}
