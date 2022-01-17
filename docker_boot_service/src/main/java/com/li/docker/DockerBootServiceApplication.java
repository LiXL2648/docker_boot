package com.li.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.li.docker.mapper")
public class DockerBootServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerBootServiceApplication.class, args);
    }
}
