package ray.playground.techcaseabnrt.persistence.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "ingredient")
@Table(name = "ingredients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    private String name;
    @OneToMany
    @JoinTable(name = "recipe_ingredients_join_table",
            joinColumns = {
                    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "recipe_ingredient_id", referencedColumnName = "recipe_ingredient_id")
            })
    private List<RecipeIngredientEntity> recipeIngredientEntities;

}
