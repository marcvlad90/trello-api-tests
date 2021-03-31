package com.tools.email;

import java.util.List;

import com.tools.entities.Mail;

public interface EmailService {

    public List<Mail> getTheLastInboxEmails(String email, String password);

    public Mail getTheLastEmailWithSubject(String email, String password, String subject);

}
