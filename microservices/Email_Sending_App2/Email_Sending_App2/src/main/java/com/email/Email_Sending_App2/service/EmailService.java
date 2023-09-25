package com.email.Email_Sending_App2.service;

import com.email.Email_Sending_App2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean forgotPassword(String email)
    {
      boolean flag=false;
      try {
          User user = this.restTemplate.getForObject("http://localhost:8081/user/forgot/" + email, User.class);
          System.out.println(user.getName());

          MimeMessage message =javaMailSender.createMimeMessage();
          MimeMessageHelper helper=new MimeMessageHelper(message);

          helper.setFrom("vshinde820540@gmail.com");
          helper.setTo(user.getEmail());
          helper.setSubject("Reset Password link");
          helper.setText("""
                  <div>
                  <a href="http://localhost:8081/user/forgetPasswordForm">Click link to reset password</a>
                  </div>
                  """.formatted(email),true);

          javaMailSender.send(message);
          flag=true;



      }//try

        catch(Exception e)
        {
            e.printStackTrace();
            //throw new RuntimeException("Unable to send password email please try again!!");
        }
        return flag;
    }
}
