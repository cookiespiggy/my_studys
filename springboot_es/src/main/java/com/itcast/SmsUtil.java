package com.itcast;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author YXD
 * @date 2020/4/2
 */
public class SmsUtil {

    /**
     * 发送验证码
     *
     * @param code  随机验证码
     * @param email 客户联系邮箱，用于接收系统发送的验证码
     * @return boolean true表示发送成功
     */
    public static boolean sends(String code, String email, JavaMailSender mailSender) {
//        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setSubject("悟能课堂");
//            mailMessage.setText("欢迎注册悟能课堂，验证码为：" + code + "，3分钟内有效，为了保障您的账户安全，请勿向他人泄露验证码");
//            mailMessage.setTo(email);
//            mailMessage.setFrom("1574784536@qq.com");
//            mailSender.send(mailMessage);
//        } catch (MailException e) {
//            e.printStackTrace();
//            throw new RuntimeException("服务器维修中，稍后再试");
//        }
//        return true;
        return true;
    }
}
