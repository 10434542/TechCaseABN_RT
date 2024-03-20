package ray.playground.techcaseabnrt.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ray.playground.techcaseabnrt.domain.models.DishType;
import ray.playground.techcaseabnrt.domain.models.Recipe;
import ray.playground.techcaseabnrt.domain.models.RecipeIngredient;
import ray.playground.techcaseabnrt.exceptions.ResourceNotFoundException;
import ray.playground.techcaseabnrt.mappers.DishTypeMapper;
import ray.playground.techcaseabnrt.mappers.RecipeIngredientMapper;
import ray.playground.techcaseabnrt.mappers.RecipeMapper;
import ray.playground.techcaseabnrt.persistence.repositories.RecipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeMapper mapper;
    private final DishTypeMapper dishTypeMapper;
    private final RecipeIngredientMapper recipeIngredientMapper;

    private final RecipeRepository recipeRepository;
//    // this might be redundant, since cascading effects will ensure this is also persisted
//    private final IngredientRepository ingredientRepository;
//    private final RecipeIngredientRepository recipeIngredientRepository;
//    private final UserRepository userRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        final var result = recipeRepository.save(mapper.domainToEntity(recipe));
        return mapper.entityToDomain(result);
    }

    @Transactional
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipeById(Long id) {

        return mapper.entityToDomain(recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("no recipe found with id %s", id))));
    }

    @Transactional
    public Recipe updateRecipe(Recipe recipe) {
        final var recipeEntity = mapper.domainToEntity(recipe);
        return mapper.entityToDomain(recipeRepository.save(recipeEntity));

    }

    @Transactional
    public List<Recipe> getRecipes(DishType dishType, Integer servings, String text, List<RecipeIngredient> recipeIngredients) {
        final var mappedIngredients = recipeIngredientMapper.domainToEntities(recipeIngredients);
        final var initialFilteredRecipes = recipeRepository.findRecipeEntitiesByDishTypeAndServingsAndIngredients(dishTypeMapper.toEntity(dishType), servings, mappedIngredients);
        final var filteredRecipes = initialFilteredRecipes.stream().filter(recipeEntity -> recipeEntity.getInstructions().contains(text)).toList();
        return mapper.entitiesToDomain(filteredRecipes);
    }
}
