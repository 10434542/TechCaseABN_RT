package ray.playground.techcaseabnrt.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class Recipe {

    private Long id;

    private String name;

    @JsonIgnoreProperties({"recipe"})
    private List<RecipeIngredient> ingredients;

    @JsonBackReference
    private User user;

    private DishType dishType;

    private int servings;

    private String instructions;
}
