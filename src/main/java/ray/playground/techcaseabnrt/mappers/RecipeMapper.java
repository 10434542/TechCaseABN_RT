package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ray.playground.techcaseabnrt.domain.models.Recipe;
import ray.playground.techcaseabnrt.persistence.model.RecipeEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RecipeIngredientMapper.class})
public interface RecipeMapper {

    @Mapping(target = "createdBy", source = "user")
    WebRecipe domainToWeb(Recipe recipe);

    @Mapping(target = "createdBy", source = "user")
    RecipeEntity domainToEntity(Recipe recipe);

    @Mapping(target = "user", source = "createdBy")
    Recipe webToDomain(WebRecipe recipe);

    @Mapping(target = "user", source = "createdBy")
    Recipe entityToDomain(RecipeEntity recipe);

    // collections
    List<Recipe> entitiesToDomain(List<RecipeEntity> recipes);

    List<RecipeEntity> domainToEntities(List<Recipe> recipes);

    List<WebRecipe> domainToWeb(List<Recipe> recipes);

    List<Recipe> webToDomain(List<WebRecipe> recipes);


}
