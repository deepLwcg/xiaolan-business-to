package buzz.xiaolan.shell;

import buzz.xiaolan.email.EnableEmailSender;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableEmailSender
@SpringBootApplication
public class ShellApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ShellApplication.class);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);
    }

}
