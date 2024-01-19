package com.stein.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/1/29
 */
public class SecureRandomApp {
    public static void main(String[] args) {
        // SecureRandom无法指定种子，它使用RNG（random number generator）算法。
        // JDK的SecureRandom实际上有多种不同的底层实现，有的使用安全随机种子加上伪随机数算法来产生安全的随机数，
        // 有的使用真正的随机数生成器。实际使用的时候，可以优先获取高强度的安全随机数生成器，如果没有提供，
        // 再使用普通等级的安全随机数生成器
        // 在密码学中，安全的随机数非常重要。如果使用不安全的伪随机数，所有加密体系都将被攻破。因此，时刻牢记必须使用SecureRandom来产生安全的随机数。
        try {
            SecureRandom sr = SecureRandom.getInstanceStrong();
            byte[] buffer = new byte[1024];
            sr.nextBytes(buffer);
            System.out.println(Arrays.toString(buffer));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
