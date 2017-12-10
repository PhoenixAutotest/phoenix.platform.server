package com.surenpi.autotest.phoenix;

import com.surenpi.autotest.phoenix.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableOAuth2Sso
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
public class PhoenixApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PhoenixApplication.class, args);
    }
}