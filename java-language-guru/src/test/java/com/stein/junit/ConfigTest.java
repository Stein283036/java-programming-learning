package com.stein.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 条件测试
 *
 * @author njl
 * @date 2023/2/7
 */
class ConfigTest {
	Config config;

	@BeforeEach
	void setUp() {
		config = new Config();
	}

	@AfterEach
	void tearDown() {
		config = null;
	}

	@Test
	@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9}) // 只在 JRE8 或 JRE9 的环境下执行该测试
	void testOnJRE() {
		System.out.println("只在 JRE8 或 JRE9 的环境下执行该测试");
	}

	@Test
	@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*") // 根据系统属性的条件执行测试
	void testOn64BitArch() {
		System.out.println("在64位架构上执行");
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
		// 只在 windows 环境下运行该测试
	void testOnWindows() {
		assertEquals("C:\\user.txt", config.getConfigFile("user.txt"));
	}

	@Test
	@DisabledOnJre(JRE.JAVA_8) // 不在 JRE8 的环境下执行该测试
	void testOnNotJRE8() {
		System.out.println("不在 JRE8 的环境下执行该测试");
	}

	@Test
	@EnabledOnOs({OS.LINUX, OS.MAC})
		// 只在 linux 或 mac 环境下运行该测试
	void testOnLinux() {
		assertEquals("/usr/local/user.txt", "user.txt");
	}

	/**
	 * 条件测试 @Disabled junit 会跳过该测试并会报告信息
	 * IDEA 的界面显示也会明显区分
	 */
	@Disabled
	@Test
	void t2() {
		// void com.guhe.junit.FactorialTest.t2() is @Disabled
		System.out.println("不执行这个测试");
	}
}