package com.tools.email;

import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;

import com.tools.models.Mail;
public class GmailService implements EmailService {

    private String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            result = "";
            MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
            int count = mimeMultipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result = result + "\n" + bodyPart.getContent();
                    break; // without break same text appears twice in my tests
                } else if (bodyPart.isMimeType("text/html")) {
                    String html = (String)bodyPart.getContent();
                    result = result + "\n" + Jsoup.parse(html).text();
                }
            }
            return result;
        } else if (message.isMimeType("text/html")) {
            String html = (String)message.getContent();
            result = result + "\n" + Jsoup.parse(html).text();
            return result;
        }
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Mail> getTheLastInboxEmails(String email, String password) {
        @SuppressWarnings("rawtypes")
        List mails = new ArrayList();
        String host = "pop.gmail.com";
        try {

            // create properties field
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("imaps");
            store.connect(host, email, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("Inbox");
            emailFolder.open(Folder.READ_WRITE);

            //retrieve the messages from the folder in an array and print it
            javax.mail.Message[] messages = emailFolder.getMessages();
            emailFolder.setFlags(messages, new Flags(Flags.Flag.SEEN), false);
            for (int i = messages.length - 1; (i >= (messages.length - 20)) && (i >= 0); i--) {
                Mail mailObject = new Mail();
                Message message = messages[i];
                mailObject.setSubject(message.getSubject());
                mailObject.setSender(message.getFrom()[0].toString());
                mailObject.setMailContent(getTextFromMessage(message));
                mails.add(mailObject);
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Mails size is: " + mails.size());
        return mails;
    }

    @Override
    public Mail getTheLastEmailWithSubject(String email, String password, String subject) {
        List<Mail> mails = getTheLastInboxEmails(email, password);
        for (int i = 0; i < (mails.size() - 1); i++) {
            if (mails.get(i).getSubject().contains(subject)) {
                System.out.println("The index of the email you are searching for is: " + i);
                return mails.get((i));
            }
        }
        return null;
    }
}
