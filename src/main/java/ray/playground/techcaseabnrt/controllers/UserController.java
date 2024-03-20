package ray.playground.techcaseabnrt.controllers;

import lombok.RequiredArgsConstructor;
import org.generated.recipe.api.UsersApi;
import org.generated.recipe.model.WebFavoriteRecipeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ray.playground.techcaseabnrt.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService service;

    @Override
    public ResponseEntity<Void> addFavoriteRecipe(WebFavoriteRecipeRequest webFavoriteRecipeRequest) {
        service.addRecipeToFavorites(webFavoriteRecipeRequest.getRecipeId(), webFavoriteRecipeRequest.getUserId());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        service.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeFavoriteRecipe(WebFavoriteRecipeRequest webFavoriteRecipeRequest) {
        service.removeFromFavorites(webFavoriteRecipeRequest.getRecipeId(), webFavoriteRecipeRequest.getUserId());
        return ResponseEntity.ok().build();
    }
}
