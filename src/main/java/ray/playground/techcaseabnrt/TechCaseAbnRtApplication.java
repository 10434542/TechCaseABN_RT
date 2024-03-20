package ray.playground.techcaseabnrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class TechCaseAbnRtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechCaseAbnRtApplication.class, args);
    }

}
