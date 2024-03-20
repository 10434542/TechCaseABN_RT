package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebIngredient;
import org.mapstruct.Mapper;
import ray.playground.techcaseabnrt.domain.models.Ingredient;
import ray.playground.techcaseabnrt.persistence.model.IngredientEntity;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    WebIngredient domainToWeb(Ingredient ingredient);

    //    @Mapping(target = "recipeIngredientEntities", source = "recipeIngredients")
    IngredientEntity domainToEntity(Ingredient ingredient);

    Ingredient webToDomain(WebIngredient ingredient);

    //    @Mapping(target = "recipeIngredients", source = "recipeIngredientEntities")
    Ingredient entityToDomain(IngredientEntity ingredient);
}
