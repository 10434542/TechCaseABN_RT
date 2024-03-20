package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebRecipeIngredient;
import org.mapstruct.Mapper;
import ray.playground.techcaseabnrt.domain.models.RecipeIngredient;
import ray.playground.techcaseabnrt.persistence.model.RecipeIngredientEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class, QuantityMapper.class})
public interface RecipeIngredientMapper {
    WebRecipeIngredient domainToWeb(RecipeIngredient recipe);

    RecipeIngredientEntity domainToEntity(RecipeIngredient recipe);

    RecipeIngredient webToDomain(WebRecipeIngredient recipe);

    RecipeIngredient entityToDomain(RecipeIngredientEntity recipe);

    List<RecipeIngredient> entitiesToDomain(List<RecipeIngredientEntity> entities);

    List<RecipeIngredientEntity> domainToEntities(List<RecipeIngredient> domain);

    List<RecipeIngredient> webToDomain(List<WebRecipeIngredient> ingredients);

}
