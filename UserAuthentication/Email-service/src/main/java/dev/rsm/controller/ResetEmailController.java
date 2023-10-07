package dev.rsm.controller;

import dev.rsm.dto.ResetEmailRequest;
import dev.rsm.service.ResetEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ResetEmailController {

    private final ResetEmailService resetEmailService;

    public ResetEmailController(ResetEmailService resetEmailService) {
        this.resetEmailService = resetEmailService;
    }

    @KafkaListener(topics = "reset-password-email", groupId = "reset-email")
    public void sendResetPasswordTokenEmail(ResetEmailRequest resetEmailRequest) {
        resetEmailService.sendResetPasswordTokenEmail(resetEmailRequest);
    }
}
