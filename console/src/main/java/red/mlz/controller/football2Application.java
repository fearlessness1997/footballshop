package red.mlz.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "red.mlz")
@MapperScan("red.mlz.mapper")
public class football2Application {

    public static void main(String[] args) {
        SpringApplication.run(football2Application.class, args);
    }

}
