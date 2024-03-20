package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebUser;
import org.mapstruct.Mapper;
import ray.playground.techcaseabnrt.domain.models.User;
import ray.playground.techcaseabnrt.persistence.model.UserEntity;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface UserMapper {
    WebUser domainToWeb(User user);

    UserEntity domainToEntity(User user);

    User webToDomain(WebUser user);

    User entityToDomain(UserEntity user);
}
