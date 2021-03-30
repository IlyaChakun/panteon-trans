package by.iba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@SpringBootApplication(scanBasePackages = {"by.iba"})
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class CommonServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }
}