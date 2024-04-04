package buzz.xiaolan.exception;

import buzz.xiaolan.core.contents.AppContext;
import buzz.xiaolan.core.exception.StatusCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/5
 * @Description StatusCode
 */
@Getter
@ToString
@AllArgsConstructor
public enum SimpleStatusCode implements StatusCodeInterface {



    BAD_REQUEST(HttpStatus.BAD_REQUEST, "A0002", "bad.request"),

    NO_HANDLER_FOUND(HttpStatus.NOT_FOUND, "A0003", "no.handler.found"),

    USER_REQUEST_PARAM_ERROR(HttpStatus.BAD_REQUEST, "A0444", "request.parameter.error"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "A0001", "http.request.method.not.supported"),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "A0000", "internal.server.error"),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public String getMessage() {
        return AppContext.getMessage(message,message, LocaleContextHolder.getLocale());
    }
}
