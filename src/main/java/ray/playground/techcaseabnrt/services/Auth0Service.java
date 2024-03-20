package ray.playground.techcaseabnrt.services;

import lombok.RequiredArgsConstructor;
import org.generated.recipe.model.WebAuth0LoginRequest;
import org.generated.recipe.model.WebAuth0LoginResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ray.playground.techcaseabnrt.configuration.security.Auth0Properties;
import ray.playground.techcaseabnrt.persistence.model.UserEntity;
import ray.playground.techcaseabnrt.persistence.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Auth0Service {

    // TODO: filthy dirty hack, should actually have a frontend and use the PKCE flow
    //      but we don't want a frontend for now

    private final RestTemplate restTemplate;

    private final Auth0Properties auth0Properties;

    private final UserRepository userRepository;

    private static final String PASSWORD_GRANT_TYPE = "password";

    public WebAuth0LoginResponse login(WebAuth0LoginRequest request) {
        final var url = auth0Properties.issuer() + "oauth/token";

        final var loginRequest = new LoginRequest(
                PASSWORD_GRANT_TYPE,
                request.getUsername(),
                request.getPassword(),
                auth0Properties.audience(),
                auth0Properties.clientId(),
                auth0Properties.clientSecret()
        );

        final var response = restTemplate.postForObject(url, loginRequest, LoginResponse.class);
        final var jwt = JwtDecoders.fromOidcIssuerLocation(auth0Properties.issuer()).decode(response.access_token());
        validateTokenAudience(jwt);

        final var userName = (String) jwt.getClaim("sub");
        saveUserIfNotExists(userName);

        return new WebAuth0LoginResponse().jwtToken(response.access_token());
    }

    @Transactional
    public void saveUserIfNotExists(String userName) {
        UserEntity byName = userRepository.findByName(userName);
        if (byName == null) {
            final var newUser = new UserEntity(null, userName, List.of(), List.of());
            userRepository.save(newUser);
        }
    }


    private void validateTokenAudience(Jwt jwt) {
        final var audiences = (List<?>) jwt.getClaim("aud");
        if (!audiences.contains(auth0Properties.audience())) throw new IllegalStateException("unknown audience");
    }

    private record LoginRequest(String grant_type, String username, String password, String audience,
                                String client_id,
                                String client_secret) {
    }

    private record LoginResponse(
            String access_token
    ) {
    }
}
