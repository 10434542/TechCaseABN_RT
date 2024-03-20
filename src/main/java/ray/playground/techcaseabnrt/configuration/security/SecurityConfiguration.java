package ray.playground.techcaseabnrt.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final Auth0Properties auth0Properties;

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final var converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("permissions");
        converter.setAuthorityPrefix("");
        final var jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }

    @Bean
    @Profile("!test")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/auth0/login", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/error").permitAll()
                        // spring calls it authority, auth0 calls it permission
                        .requestMatchers("/admin/*").hasAnyAuthority("access:all")
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

    @Bean
    @Profile("!test")
    public JwtDecoder jwtDecoder() {
        final var nimbusJwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(auth0Properties.issuer());
        nimbusJwtDecoder.setJwtValidator(
                new DelegatingOAuth2TokenValidator<>(new AudienceValidator(auth0Properties.audience()),
                        JwtValidators.createDefaultWithIssuer(auth0Properties.issuer()))
        );
        return nimbusJwtDecoder;
    }

    static class AudienceValidator implements OAuth2TokenValidator<Jwt> {
        private final String audience;

        AudienceValidator(String audience) {
            super();
            this.audience = audience;
        }


        @Override
        public OAuth2TokenValidatorResult validate(Jwt token) {
            final var audiences = token.getAudience();
            if (audiences.contains(audience)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN));
        }
    }
}
