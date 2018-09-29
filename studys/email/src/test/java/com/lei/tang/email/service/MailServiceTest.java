package com.lei.tang.email.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author tanglei
 * @date 18/9/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    private final String to = "";

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendTextMessage() {
        mailService.sendTextMessage(to, "测试文本邮件", "测试文本邮件");
    }

    @Test
    public void sendHTMLMessage() {
        String content = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n <meta charset=\"UTF-8\">\n<title>Title" +
                "</title>\n</head>\n<body>\n<h1>这是一封HTML邮件</h1>\n</body>\n</html>";
        mailService.sendHTMLMessage(to, "HTML邮件", content);
    }

    @Test
    public void sendAttachment() {
        String filePath = "";
        mailService.sendAttachmentMessage(to, "带附件", "带附件的邮件", filePath);
    }

    @Test
    public void sendInlineMessage() {
        String rscId = "";
        String rscPath = "";
        mailService.sendInlineMessage(to, "带图片", "带图片的邮件", rscId, rscPath);
    }

    @Test
    public void sendTemplateMessage(){
        Context context = new Context();
        context.setVariable("ck", "Mrtanglei");
        String content = templateEngine.process("emailTemplate", context);
        mailService.sendHTMLMessage("qiujieee_empty@outlook.com", "模板邮件", content);
    }
}