package com.tools.email;

import java.util.List;

import com.tools.entities.Mail;

public class EmailProcessor {
    private EmailService emailService;

    public EmailProcessor(EmailService emailService) {
        this.emailService = emailService;
    }

    public List<Mail> getTheLastInboxEmails(String email, String password) {
        return this.emailService.getTheLastInboxEmails(email, password);
    }

    public Mail getTheLastEmailWithSubject(String email, String password, String subject) {
        return this.emailService.getTheLastEmailWithSubject(email, password, subject);
    }

}
