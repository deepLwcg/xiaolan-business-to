package buzz.xiaolan.exception;

import buzz.xiaolan.core.CoreConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/4
 * @Description EnableExceptionHandler
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CoreConfiguration.class, GlobalExceptionConfiguration.class})
public @interface EnableExceptionHandler {

}
