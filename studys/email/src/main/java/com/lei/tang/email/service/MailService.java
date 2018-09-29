package com.lei.tang.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author tanglei
 * @date 18/9/29
 */
@Service
@Slf4j
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送普通文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendTextMessage(String to, String subject, String content) {
        Assert.hasText(to, "收件人邮箱为空");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
            log.info("发送文本邮件成功");
        } catch (Exception e) {
            log.error("发送文本邮件失败", e);
        }
    }

    /**
     * 发送普通html邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendHTMLMessage(String to, String subject, String content) {
        Assert.hasText(to, "收件人邮箱为空");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
            log.info("发送html邮件成功");
        } catch (MessagingException e) {
            log.error("发送html邮件失败", e);
        }
    }

    /**
     * 发送附件邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentMessage(String to, String subject, String content, String filePath) {
        Assert.hasText(to, "收件人邮箱为空");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            //收件方为多个
            //helper.setTo(String[]{});
            helper.setSubject(subject);
            helper.setText(content);
            if (StringUtils.hasText(filePath)) {
                FileSystemResource resource = new FileSystemResource(filePath);
                helper.addAttachment(resource.getFilename(), resource);
                //添加多个附件
                //helper.addAttachment(resource.getFilename(),resource);
            }
            javaMailSender.send(mimeMessage);
            log.info("发送附件邮件成功");
        } catch (MessagingException e) {
            log.error("发送html邮件失败", e);
        }
    }

    /**
     * 发送静态资源邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscId
     * @param rscPath
     */
    public void sendInlineMessage(String to, String subject, String content, String rscId, String rscPath) {
        Assert.hasText(to, "收件人邮箱为空");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            FileSystemResource resource = new FileSystemResource(rscPath);
            helper.addInline(rscId, resource);
            //发送多个
            //helper.addInline(rscId, resource2);
            javaMailSender.send(mimeMessage);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发送异常", e);
        }
    }
}
