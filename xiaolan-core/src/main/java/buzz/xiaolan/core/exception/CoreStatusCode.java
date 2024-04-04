package buzz.xiaolan.core.exception;

import buzz.xiaolan.core.contents.AppContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/5
 * @Description SimpleStatusCode
 */
@Getter
@ToString
@AllArgsConstructor
public enum CoreStatusCode implements StatusCodeInterface{

    SUCCESS(HttpStatus.OK, "00000", "success"),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public String getMessage() {
        return AppContext.getMessage(message,message, LocaleContextHolder.getLocale());
    }
}
