package ray.playground.techcaseabnrt.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Ingredient {

    private Long id;

    private String name;

    // maybe just ignore this field entirely
    @JsonIgnoreProperties({"ingredient"})
    private List<RecipeIngredient> recipeIngredients;

}
