package buzz.xiaolan.core.contents;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/5
 * @Description AppContext
 */
@Configuration
@ConditionalOnBean(AppContext.class)
public class AppContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        AppContext.applicationContext = applicationContext;
    }


    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    @SuppressWarnings("all")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static String getMessage(String message, String defaultMessage, Locale locale) {
        try {
            MessageSource messageSource = applicationContext.getBean(MessageSource.class);
            return messageSource.getMessage(message, null, defaultMessage, locale);
        } catch (Exception ignored) {
            return defaultMessage;
        }
    }
}
