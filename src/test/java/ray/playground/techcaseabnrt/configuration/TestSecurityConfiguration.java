package ray.playground.techcaseabnrt.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@TestConfiguration
public class TestSecurityConfiguration {

    @Bean
    @Profile("test")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
                .build();
    }

    @Bean
    @Profile("test")
    JwtDecoder jwtDecoder() {
        System.out.println("setting up some security");
        return new MockJwtDecoder();
    }

    static class MockJwtDecoder implements JwtDecoder {

        @Override
        public Jwt decode(String token) throws JwtException {
            return new Jwt(
                    "token",
                    Instant.now(),
                    Instant.now().plusSeconds(30),
                    Map.of("wew", "hell nah"),
                    Map.of("permissions", List.of("access:all"))

            );
        }
    }

}
