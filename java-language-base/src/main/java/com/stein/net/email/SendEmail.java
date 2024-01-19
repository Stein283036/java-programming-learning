package com.stein.net.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * @author njl
 * @date 2023/2/7
 */
public class SendEmail {
    public static void main(String[] args) throws MessagingException {
        s1();
        // pqopcwbuewobbbfg
    }

    public static void s1() throws MessagingException {
        String smtp = "smtp.qq.com";
        String username = "597061520@qq.com";
        String password = "pqopcwbuewobbbfg";

        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密

        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // 设置debug模式便于调试:
        session.setDebug(true);
        System.out.println("session = " + session);


        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress(username));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("stein283036@gmail.com"));
        // 设置邮件主题:
        message.setSubject("Hello", "UTF-8");
        // 设置邮件正文:
        message.setText("<a href='https://www.baidu.com'>去百度吧</a>", "UTF-8", "html");
        // 发送:
        Transport.send(message);
    }
}
