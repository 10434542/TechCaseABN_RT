package ray.playground.techcaseabnrt.mappers;

import org.generated.recipe.model.WebQuantityType;
import org.springframework.stereotype.Service;
import ray.playground.techcaseabnrt.domain.models.QuantityType;

import static ray.playground.techcaseabnrt.domain.models.QuantityType.*;

@Service
public class QuantityMapper {
    public QuantityType toDomain(String quantityType) {
        return switch (quantityType) {
            case "KILOGRAMS" -> KILOGRAMS;
            case "GRAMS" -> GRAMS;
            case "LITERS" -> LITERS;
            case "MILLILITERS" -> MILLILITERS;
            case "ABSOLUTE" -> ABSOLUTE;
            case "SPOONS" -> SPOONS;
            case "TEASPOONS" -> TEASPOONS;
            default -> throw new IllegalStateException("Unexpected value: " + quantityType);
        };
    }

    public String toEntity(QuantityType quantityType) {
        return switch (quantityType) {
            case KILOGRAMS -> "KILOGRAMS";
            case GRAMS -> "GRAMS";
            case LITERS -> "LITERS";
            case MILLILITERS -> "MILLILITERS";
            case ABSOLUTE -> "ABSOLUTE";
            case SPOONS -> "SPOONS";
            case TEASPOONS -> "TEASPOONS";
        };
    }

    public QuantityType webToDomain(WebQuantityType quantityType) {
        return switch (quantityType) {
            case KILOGRAMS -> KILOGRAMS;
            case GRAMS -> GRAMS;
            case LITERS -> LITERS;
            case MILLILITERS -> MILLILITERS;
            case ABSOLUTE -> ABSOLUTE;
            case SPOONS -> SPOONS;
            case TEASPOONS -> TEASPOONS;
        };
    }
}
