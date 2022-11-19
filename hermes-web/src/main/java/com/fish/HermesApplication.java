package com.fish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author szx
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.fish.hermes.*.mapper")
public class HermesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HermesApplication.class, args);
    }
}
