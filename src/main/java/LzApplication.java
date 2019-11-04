import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.luozhe.lzspring.mapper")
@ComponentScan("org.luozhe.*")
@ServletComponentScan
public class LzApplication {
    public static void main(String[] args) {
        SpringApplication.run(LzApplication.class, args);
    }
}
