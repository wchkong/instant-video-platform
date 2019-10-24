package com.wchkong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: kongweichang
 * @Date: 2019/10/24 10:16
 */
@SpringBootApplication
@EnableEurekaServer
public class PlatformEurekaApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(PlatformEurekaApplicaton.class, args);
    }

}
