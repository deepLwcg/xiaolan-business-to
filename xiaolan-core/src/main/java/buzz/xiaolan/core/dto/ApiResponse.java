package buzz.xiaolan.core.dto;

import buzz.xiaolan.core.exception.BizException;
import buzz.xiaolan.core.exception.CoreStatusCode;
import buzz.xiaolan.core.exception.StatusCodeInterface;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/5
 * @Description ApiResponse
 */
@Data
@Builder
@ToString
public class ApiResponse<T> {

    protected String code;

    protected String message;

    protected T data;

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        CoreStatusCode statusCode = CoreStatusCode.SUCCESS;
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(StatusCodeInterface statusCode) {
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build();
    }

    public static <T> ApiResponse<T> fail(StatusCodeInterface statusCode,String message) {
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> fail(BizException bizException) {
        return fail(bizException, null);
    }

    public static <T> ApiResponse<T> fail(BizException bizException, T data) {
        StatusCodeInterface statusCode = bizException.getStatusCode();
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }
}
