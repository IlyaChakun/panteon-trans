package by.iba;

import com.netflix.appinfo.AmazonInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(scanBasePackages = {"by.iba"})
@EnableFeignClients(basePackages = "by.iba.client")
@EnableCircuitBreaker
@EnableEurekaClient
@Slf4j
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	@Autowired
	@Profile("prod")
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
		log.info("** Inside eurekaInstanceConfigBean **");
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setDataCenterInfo(info);
		log.info("*** LOCAL HOSTNAME: {}", info.get(AmazonInfo.MetaDataKey.localHostname));
		log.info("*** LOCAL IP: {} ", info.get(AmazonInfo.MetaDataKey.localIpv4));
		log.info("*** PUBLIC HOSTNAME: {}", info.get(AmazonInfo.MetaDataKey.publicHostname));
		log.info("*** PUBLIC IP: {}", info.get(AmazonInfo.MetaDataKey.publicIpv4));
		return config;
	}

}
