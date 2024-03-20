package ray.playground.techcaseabnrt.configuration.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth0")
public record Auth0Properties(
        String domain,
        String clientId,
        String clientSecret,
        String audience,
        String issuer
) {
}
