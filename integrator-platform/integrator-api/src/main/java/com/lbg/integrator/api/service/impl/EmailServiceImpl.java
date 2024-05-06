package com.lbg.integrator.api.service.impl;


import com.lbg.integrator.api.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String[] toEmail, String[] cc, String subject, String body, MultipartFile[] attachments) {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(this.fromEmail);
            mimeMessageHelper.setTo(toEmail);
            if (cc != null && cc.length > 0)
                mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            for (int i = 0; i < attachments.length; i++)
                mimeMessageHelper.addAttachment(attachments[i]
                        .getOriginalFilename(), (InputStreamSource) new ByteArrayResource(attachments[i]
                        .getBytes()));
            this.mailSender.send(mimeMessage);
            System.out.println("Mail Send to " + toEmail + " successfully ");
        } catch (Exception e) {
            System.out.println("Error during exception " + e);
        }
    }
}
