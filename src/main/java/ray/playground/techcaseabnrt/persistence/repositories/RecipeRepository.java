package ray.playground.techcaseabnrt.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ray.playground.techcaseabnrt.persistence.model.DishType;
import ray.playground.techcaseabnrt.persistence.model.RecipeEntity;
import ray.playground.techcaseabnrt.persistence.model.RecipeIngredientEntity;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    // filter on instructions is custom and will be done in service layer for convenience
    List<RecipeEntity> findRecipeEntitiesByDishTypeAndServingsAndIngredients(String dishType, Integer servings, List<RecipeIngredientEntity> ingredients);
}
