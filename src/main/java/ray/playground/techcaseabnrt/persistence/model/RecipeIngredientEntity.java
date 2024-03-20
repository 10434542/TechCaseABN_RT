package ray.playground.techcaseabnrt.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "recipe_ingredient")
@Table(name = "recipe_ingredients")
@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @Embedded
    private QuantityEntity quantity;
}
