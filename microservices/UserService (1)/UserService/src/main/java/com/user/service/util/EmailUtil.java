//package com.user.service.util;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailUtil{
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//    public void sendSetPasswordEmail(String email) throws MessagingException {
////        SimpleMailMessage message = new SimpleMailMessage();
////
////        message.setTo(email);
////        message.setSubject("Set Password");
////        message.setText("""
////
////                <div>
////                <a href="http://localhost:8081/set-password?email=%s" target="_blank">Click link to set password</a>
////                </div>
////
////                """.formatted(email));
//
//          MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//
//          MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
//
//        mimeMessageHelper.setTo(email);
//        mimeMessageHelper.setSubject("Set Password");
//        mimeMessageHelper.setText("""
//
//                <div>
//                <a href="http://localhost:8081/set-password?email=%s" target="_blank">Click link to set password</a>
//                </div>
//
//                """.formatted(email));
//
//
//
//
//
//        javaMailSender.send(mimeMessage);
//    }
//}
