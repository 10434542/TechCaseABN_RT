package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebDishType;
import org.springframework.stereotype.Service;
import ray.playground.techcaseabnrt.domain.models.DishType;

import static ray.playground.techcaseabnrt.domain.models.DishType.*;

@Service
public class DishTypeMapper {
    public DishType toDomain(String dishType) {
        return switch (dishType) {

            case "VEGETARIAN" -> VEGETARIAN;
            case "VEGAN" -> VEGAN;
            case "OTHER" -> OTHER;
            default -> throw new IllegalStateException("Unexpected value: " + dishType);
        };
    }

    public String toEntity(DishType dishType) {
        return switch (dishType) {

            case VEGETARIAN -> "VEGETARIAN";
            case VEGAN -> "VEGAN";
            case OTHER -> "OTHER";
        };
    }

    public DishType webToDomain(WebDishType dishType) {
        return switch (dishType) {

            case VEGETARIAN -> VEGETARIAN;
            case VEGAN -> VEGAN;
            case OTHER -> OTHER;
        };
    }
}
