package ray.playground.techcaseabnrt.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RecipeIngredient {

    private Long id;

    private String name;

    // deze eruit
    @JsonIgnoreProperties({"ingredients"})
    private Recipe recipe;

    @JsonIgnoreProperties({"recipeIngredients"})
    private Ingredient ingredient;

    private Quantity quantity;
}
