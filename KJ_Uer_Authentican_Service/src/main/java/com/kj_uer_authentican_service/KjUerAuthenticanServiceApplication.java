package com.kj_uer_authentican_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KjUerAuthenticanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KjUerAuthenticanServiceApplication.class, args);
    }

}
