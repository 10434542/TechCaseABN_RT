package ray.playground.techcaseabnrt.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "recipe")
@Table(name = "recipes")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredientEntity> ingredients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    private String dishType;

    private int servings;

    private String instructions;
}
