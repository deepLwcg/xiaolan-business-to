package buzz.xiaolan.shell.commands;

import buzz.xiaolan.core.contents.AppContext;
import buzz.xiaolan.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/5
 * @Description Command
 */
@Slf4j
@ShellComponent
public class Command {


    @ShellMethod
    public void hello() {
        GlobalExceptionHandler globalExceptionHandler = AppContext.getBean(GlobalExceptionHandler.class);
        log.warn("globalExceptionHandler: {}", globalExceptionHandler == null);
    }

}
