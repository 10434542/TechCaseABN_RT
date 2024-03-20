package ray.playground.techcaseabnrt.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.generated.recipe.api.RecipesApi;
import org.generated.recipe.model.WebDishType;
import org.generated.recipe.model.WebRecipe;
import org.generated.recipe.model.WebRecipeIngredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ray.playground.techcaseabnrt.mappers.DishTypeMapper;
import ray.playground.techcaseabnrt.mappers.RecipeIngredientMapper;
import ray.playground.techcaseabnrt.mappers.RecipeMapper;
import ray.playground.techcaseabnrt.services.RecipeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeController implements RecipesApi {
    private final RecipeMapper recipeMapper;
    private final RecipeIngredientMapper recipeIngredientMapper;
    private final DishTypeMapper dishMapper;
    private final RecipeService service;


    @Override
    public ResponseEntity<WebRecipe> createRecipe(WebRecipe webRecipe) {
        return ResponseEntity.ok(recipeMapper.domainToWeb(service.createRecipe(recipeMapper.webToDomain(webRecipe))));
    }

    @Override
    public ResponseEntity<WebRecipe> getARecipeById(Long recipeId) {
        return ResponseEntity.ok(recipeMapper.domainToWeb(service.getRecipeById(recipeId)));
    }

    @Override
    public ResponseEntity<List<WebRecipe>> getRecipes(WebDishType dishType, Integer numberOfServings, String text, List<@Valid WebRecipeIngredient> ingredients) {
        final var result = service.getRecipes(dishMapper.webToDomain(dishType), numberOfServings, text, recipeIngredientMapper.webToDomain(ingredients));
        return ResponseEntity.ok(recipeMapper.domainToWeb(result));
    }

    @Override
    public ResponseEntity<Void> removeRecipe(Long recipeId) {
        service.deleteRecipeById(recipeId);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<Void> updateRecipe(WebRecipe webRecipe) {
        service.updateRecipe(recipeMapper.webToDomain(webRecipe));
        return ResponseEntity.ok().build();
    }
}
