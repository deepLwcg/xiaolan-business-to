package buzz.xiaolan.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/2
 * @Description EmailSenderConfiguration
 */

@ComponentScan(basePackages = "buzz.xiaolan.email")
public class EmailSenderConfiguration {


    @Value("${spring.mail.username:xiaolan}")
    private String from;

    @Bean
    @ConditionalOnProperty(prefix = "spring.mail", name = "host")
    public MailSenderHelper mailSenderHelper(JavaMailSender javaMailSender,
                                             TemplateEngine templateEngine) {

        System.setProperty("mail.mime.splitlongparameters", "false");
        System.setProperty("mail.mime.charset", "UTF-8");

        MailSenderHelper mailSenderHelper = new MailSenderHelper();
        mailSenderHelper.setJavaMailSender(javaMailSender);
        mailSenderHelper.setTemplateEngine(templateEngine);
        mailSenderHelper.setFrom(from);
        return mailSenderHelper;
    }
}
