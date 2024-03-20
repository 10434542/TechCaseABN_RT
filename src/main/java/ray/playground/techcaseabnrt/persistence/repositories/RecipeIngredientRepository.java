package ray.playground.techcaseabnrt.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ray.playground.techcaseabnrt.persistence.model.RecipeIngredientEntity;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredientEntity, Long> {
}
