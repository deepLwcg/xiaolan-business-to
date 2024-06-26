package buzz.xiaolan.email;

import buzz.xiaolan.core.CoreConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/2
 * @Description EnableEmailSender
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CoreConfiguration.class,EmailSenderConfiguration.class})
public @interface EnableEmailSender {

}
