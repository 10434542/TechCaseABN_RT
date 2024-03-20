package ray.playground.techcaseabnrt.domain.models;

public enum DishType {
    VEGETARIAN,
    VEGAN,
    OTHER;

    public static DishType toDomain(ray.playground.techcaseabnrt.persistence.model.DishType dishType) {
        return switch (dishType) {

            case VEGETARIAN -> VEGETARIAN;
            case VEGAN -> VEGAN;
            case OTHER -> OTHER;
        };
    }
}
