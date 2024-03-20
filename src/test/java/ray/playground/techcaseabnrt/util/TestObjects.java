package ray.playground.techcaseabnrt.util;

import org.generated.recipe.model.WebDishType;
import org.generated.recipe.model.WebRecipe;
import org.generated.recipe.model.WebUser;
import ray.playground.techcaseabnrt.domain.models.Recipe;
import ray.playground.techcaseabnrt.domain.models.User;
import ray.playground.techcaseabnrt.persistence.model.DishType;
import ray.playground.techcaseabnrt.persistence.model.RecipeEntity;
import ray.playground.techcaseabnrt.persistence.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class TestObjects {
    public static final RecipeEntity recipeEntity = RecipeEntity.builder()
            .createdBy(null)
            .id(1L)
            .name("yuk")
            .dishType("VEGAN")
            .instructions("dont cook it")
            .servings(1)
            .ingredients(List.of())
            .build();
    public static final WebUser webUser = new WebUser()
            .createdRecipes(new ArrayList<>())
            .favoriteRecipes(new ArrayList<>())
            .id(1L)
            .name("hank");

    public static final WebRecipe webRecipe = new WebRecipe()
            .id(1L)
            .name("yuk")
            .ingredients(List.of())
            .instructions("dont cook it")
            .dishType(WebDishType.VEGAN)
            .servings(1)
            .createdBy(webUser);




    public static final User user = User.builder()
                            .createdRecipes(new ArrayList<>())
                            .favoriteRecipes(new ArrayList<>())
                            .id(1L)
                            .name("hank")
                            .build();
    public static final Recipe recipe = Recipe.builder()
            .user(user)
            .id(1L)
            .dishType(ray.playground.techcaseabnrt.domain.models.DishType.VEGAN)
            .name("yuk")
            .instructions("dont cook it")
            .servings(1)
            .ingredients(List.of())
            .build();
    public static final UserEntity userEntity = UserEntity.builder()
                                    .createdRecipes(new ArrayList<>())
                                    .favoriteRecipes(new ArrayList<>())
                                    .id(1L)
                                    .name("hank")
                                    .build();

    private TestObjects() {
        // prevent instantiation
    }
}
