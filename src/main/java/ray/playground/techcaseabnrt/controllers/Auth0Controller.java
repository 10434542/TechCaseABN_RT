package ray.playground.techcaseabnrt.controllers;

import lombok.RequiredArgsConstructor;
import org.generated.recipe.api.AuthenticationApi;
import org.generated.recipe.model.WebAuth0LoginRequest;
import org.generated.recipe.model.WebAuth0LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import ray.playground.techcaseabnrt.services.Auth0Service;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth0")
public class Auth0Controller implements AuthenticationApi {

    private final Auth0Service auth0Service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AuthenticationApi.super.getRequest();
    }

    @Override
    public ResponseEntity<WebAuth0LoginResponse> authenticate(WebAuth0LoginRequest webAuth0LoginRequest) {
        return ResponseEntity.ok(auth0Service.login(webAuth0LoginRequest));
    }
}
