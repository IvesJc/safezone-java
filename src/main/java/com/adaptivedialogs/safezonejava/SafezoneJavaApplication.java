package com.adaptivedialogs.safezonejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SafezoneJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafezoneJavaApplication.class, args);
    }

}
