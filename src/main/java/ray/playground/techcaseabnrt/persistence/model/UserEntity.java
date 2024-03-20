package ray.playground.techcaseabnrt.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @OneToMany
    private List<RecipeEntity> createdRecipes;

    @ManyToMany
    @JoinTable(name = "favorite_recipes_join_table",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id"),
            }
    )
    private List<RecipeEntity> favoriteRecipes;
}
