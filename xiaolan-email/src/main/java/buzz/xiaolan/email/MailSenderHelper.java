package buzz.xiaolan.email;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/2
 * @Description MailSenderHelper
 */
@Slf4j
public class MailSenderHelper {


    @Getter
    private static JavaMailSender javaMailSender;

    @Getter
    private static TemplateEngine templateEngine;

    private static String from;

    /**
     * send text email
     *
     * @param to      recipients
     * @param subject theme
     * @param content content
     * @date 2024/4/2 23:15
     */
    public static void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * send a simple email with attachments
     *
     * @param to      recipients
     * @param subject theme
     * @param content content
     * @param path    attachment
     * @date 2024/4/2 23:14
     */
    public static void sendSimpleMailAttachment(String to, String subject, String content, String path) {
        sendMail(to, subject, content, false, path);
    }

    /**
     * send a simple email with attachments
     *
     * @param to             recipients
     * @param subject        theme
     * @param content        content
     * @param path           attachment
     * @param attachmentName attachment name
     * @date 2024/4/2 23:13
     */
    public static void sendSimpleMailAttachment(String to, String subject, String content, String attachmentName, String path) {
        sendMail(to, subject, content, false, attachmentName, path);
    }

    /**
     * send html template email
     *
     * @param to      recipients
     * @param subject theme
     * @param content html content
     * @date 2024/4/2 23:12
     */
    public static void sendHtmlMail(String to, String subject, String content) {
        sendMail(to, subject, content, true, null);
    }

    /**
     * send html template email with attachments
     *
     * @param to      recipients
     * @param subject theme
     * @param content html content
     * @param path    attachment
     * @date 2024/4/2 23:11
     */
    public static void sendHtmlMailAttachment(String to, String subject, String content, String path) {
        sendMail(to, subject, content, true, path);
    }

    /**
     * send html template email
     *
     * @param to           recipients
     * @param subject      theme
     * @param templatePath template
     * @param params       template parameters
     * @date 2024/4/2 23:09
     */
    public static void sendHtmlMail(String to, String subject, String templatePath, Map<String, Object> params) {
        Context context = new Context();
        context.setVariables(params);
        String process = templateEngine.process(templatePath, context);
        sendHtmlMail(to, subject, process);
    }


    /**
     * send html template email with attachments
     *
     * @param to           recipients
     * @param subject      theme
     * @param templatePath template
     * @param params       template parameters
     * @param path         attachment
     * @date 2024/4/2 23:08
     */
    public static void sendHtmlMailAttachment(String to, String subject, String templatePath, Map<String, Object> params, String path) {
        Context context = new Context();
        context.setVariables(params);
        String process = templateEngine.process(templatePath, context);
        sendHtmlMailAttachment(to, subject, process, path);
    }

    /**
     * send html template email with attachments
     *
     * @param to             recipients
     * @param subject        theme
     * @param templatePath   template
     * @param params         template parameters
     * @param path           attachment
     * @param attachmentName attachment name
     * @date 2024/4/2 23:07
     */
    public static void sendHtmlMailAttachment(String to, String subject, String templatePath, Map<String, Object> params, String attachmentName, String path) {
        Context context = new Context();
        context.setVariables(params);
        String process = templateEngine.process(templatePath, context);
        sendHtmlMailAttachment(to, subject, process, attachmentName, path);
    }

    /**
     * Send HTML template email with attachments
     *
     * @param to             recipients
     * @param subject        theme
     * @param content        html content
     * @param path           attachment
     * @param attachmentName attachment name
     * @date 2024/4/2 23:06
     */
    public static void sendHtmlMailAttachment(String to, String subject, String content, String attachmentName, String path) {
        sendMail(to, subject, content, true, attachmentName, path);
    }


    /**
     * Sending Email
     *
     * @param to      recipient
     * @param subject theme
     * @param content content
     * @param html    is it html
     * @param path    attachment
     * @date 2024/4/2 23:05
     */
    public static void sendMail(String to, String subject, String content, boolean html, String path) {
        sendMail(to, subject, content, html, null, path);
    }

    /**
     * Sending Email
     *
     * @param to             recipient
     * @param subject        theme
     * @param content        content
     * @param isHtml         is it html
     * @param attachmentName attachment name
     * @param path           Attachment (supports HTTP/HTTPS connections, network file attachment name cannot be empty)
     * @date 2024/4/2 23:04
     */
    public static void sendMail(String to, String subject, String content, boolean isHtml, String attachmentName, String path) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setText(content, isHtml);
            if (StringUtils.isNotBlank(path)) {
                //携带附件
                if (isUrl(path)) {
                    UrlResource urlResource = new UrlResource(path);
                    if (StringUtils.isNotBlank(attachmentName)) {
                        mimeMessageHelper.addAttachment(attachmentName, urlResource);
                    } else {
                        log.error("attachment is http/https, {} from {} to {} send email failure: attachmentName is null", subject, from, to);
                        return;
                    }
                } else {
                    FileSystemResource file = new FileSystemResource(path);
                    if (StringUtils.isNotBlank(attachmentName)) {
                        mimeMessageHelper.addAttachment(attachmentName, file);
                    } else {
                        String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
                        mimeMessageHelper.addAttachment(fileName, file);
                    }
                }
            }
            javaMailSender.send(mimeMessage);
            log.info("{} from {} to {} send email success", subject, from, to);
        } catch (MessagingException | MalformedURLException e) {
            log.info("{} from {} to {} send email failure", subject, from, to, e);
        }
    }

    private static boolean isUrl(String path) {
        try {
            URL url = new URL(path);
            String protocol = url.getProtocol();
            return "https".equals(protocol) || "http".equals(protocol);
        } catch (Exception ignore) {
            return false;
        }
    }

    protected void setJavaMailSender(JavaMailSender javaMailSender) {
        MailSenderHelper.javaMailSender = javaMailSender;
    }

    protected void setTemplateEngine(TemplateEngine templateEngine) {
        MailSenderHelper.templateEngine = templateEngine;
    }

    protected void setFrom(String from) {
        MailSenderHelper.from = from;
    }
}
