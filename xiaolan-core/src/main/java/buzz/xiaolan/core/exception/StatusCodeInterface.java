package buzz.xiaolan.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/4
 * @Description StatusCodeInterface
 */
public interface StatusCodeInterface {

    default HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    String getCode();

    String getMessage();

}
