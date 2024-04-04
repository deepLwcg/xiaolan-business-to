package buzz.xiaolan.core.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/4
 * @Description BizException
 */
@Getter
@ToString
public class BizException extends RuntimeException {

    protected final StatusCodeInterface statusCode;

    public BizException(StatusCodeInterface statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
