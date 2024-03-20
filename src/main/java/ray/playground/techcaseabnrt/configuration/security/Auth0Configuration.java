package ray.playground.techcaseabnrt.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class Auth0Configuration {

    private final Auth0Properties auth0Properties;

    @Bean
    public RestTemplate auth0RestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(auth0Properties.issuer()).build();
    }
}
