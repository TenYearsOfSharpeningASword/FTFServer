package com.example.FTF.units.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class EmailServer {

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void sendEmail(String email) {
        Random ne = new Random();
        String code = ne.nextInt(9999 - 1000 + 1) + 1000 + "";
        this.sendEmail(code, email);
    }
    public void sendEmail(String code, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("由FTF开发团队提供的验证码");//主题
        mailMessage.setText(code);//内容
        mailMessage.setFrom(username); //发送者
        mailMessage.setTo(email); //接受者
        javaMailSender.send(mailMessage);
    }
}
