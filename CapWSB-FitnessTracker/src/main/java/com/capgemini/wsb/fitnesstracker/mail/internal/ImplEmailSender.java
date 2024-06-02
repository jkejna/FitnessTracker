package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

class ImplEmailSender implements EmailSender {
    private final JavaMailSender javaMailSender;

    @Override
    public void send(EmailDto email) {
        final SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(email.toAddress());
        simpleMail.setSubject(email.subject());
        simpleMail.setText(email.content());
        javaMailSender.send(simpleMail);
    }
}
