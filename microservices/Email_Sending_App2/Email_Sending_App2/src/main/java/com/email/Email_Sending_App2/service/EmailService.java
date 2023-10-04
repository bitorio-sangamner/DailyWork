package com.email.Email_Sending_App2.service;

import com.email.Email_Sending_App2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean updateResetPasswordToken(String NewrandomToken, String email)
    {
      boolean flag=false;
      try {

         // User user = this.restTemplate.getForObject("http://USER-SERVICE/user/forgot/" + email, User.class);
          User user = this.restTemplate.getForObject("http://USER-SERVICE/user/updateResetPasswordToken/" + NewrandomToken + "/" + email, User.class);

          System.out.println(user.getName());

          //String resetPasswordLink ="user/reset_password?token=" + NewrandomToken;
          String resetPasswordLink ="http://localhost:8081/user/reset_password?token=" + NewrandomToken;


          MimeMessage message =javaMailSender.createMimeMessage();
          MimeMessageHelper helper=new MimeMessageHelper(message);

          String content = "<p>Hello,</p>"
                  + "<p>You have requested to reset your password.</p>"
                  + "<p>Click the link below to change your password:</p>"
                  + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                  + "<br>"
                  + "<p>Ignore this email if you do remember your password, "
                  + "or you have not made the request.</p>";

          helper.setFrom("vshinde820540@gmail.com");
          helper.setTo(user.getEmail());
          helper.setSubject("Reset Password link");
//          helper.setText("""
//                  <div>
//                  <a href="http://USER-SERVICE/user/forgetPasswordForm">Click link to reset password</a>
//                  </div>
//                  """.formatted(email),true);

          helper.setText(content, true);

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
