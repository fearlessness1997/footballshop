package red.mlz.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "red.mlz")
@MapperScan("red.mlz.mapper")
public class Football2Application {

    public static void main(String[] args) {
        SpringApplication.run(Football2Application.class, args);
    }

}
