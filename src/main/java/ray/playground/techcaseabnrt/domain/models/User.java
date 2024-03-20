package ray.playground.techcaseabnrt.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class User {

    private Long id;

    private String name;

    @JsonIgnoreProperties("user")
    private List<Recipe> createdRecipes;

    @JsonIgnoreProperties("user")
    private List<Recipe> favoriteRecipes;
}
