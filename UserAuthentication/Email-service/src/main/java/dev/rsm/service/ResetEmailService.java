package dev.rsm.service;

import dev.rsm.dto.ResetEmailRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ResetEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public ResetEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendResetPasswordTokenEmail(ResetEmailRequest resetEmailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("noreply.com", "User Support");
            helper.setTo(resetEmailRequest.recipientEmail());

            String subject = "Password Reset Token";

            String content = "<p>Hello,</p>"
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Below is your reset password token:</p>"
                    + "<p>" + resetEmailRequest.token() + "</p>"
                    + "<br>"
                    + "<p>Ignore this email if you have not requested for reset password, ";

            helper.setSubject(subject);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
