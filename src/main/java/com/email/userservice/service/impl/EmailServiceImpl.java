package com.email.userservice.service.impl;

import com.email.userservice.service.EmailService;
import com.email.userservice.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account verification";
    public static final String UTF_8 = "UTF-8";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name, host, token));

            emailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachments(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, token));
            //ADD ATTACHMENTS
            FileSystemResource testImage = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/TestImage.jpg"));
            FileSystemResource testDoc = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/TestDoc.docx"));
            helper.addAttachment(testImage.getFilename(), testImage);
            helper.addAttachment(testDoc.getFilename(), testDoc);

            emailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, token));
            //ADD ATTACHMENTS
            FileSystemResource testImage = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/TestImage.jpg"));
            FileSystemResource testDoc = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/TestDoc.docx"));
            helper.addInline(getContentId(testImage.getFilename()), testImage);
            helper.addInline(getContentId(testDoc.getFilename()), testDoc);

            emailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {

    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    private String getContentId( String filename){
        return "<" + filename + ">";
    }
}
