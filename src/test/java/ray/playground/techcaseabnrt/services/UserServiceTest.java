package ray.playground.techcaseabnrt.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ray.playground.techcaseabnrt.persistence.repositories.RecipeRepository;
import ray.playground.techcaseabnrt.persistence.repositories.UserRepository;
import ray.playground.techcaseabnrt.util.TestObjects;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void whenDeleteThenExpectRepositoryIsCalled() {
        userService.deleteUserById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void whenAddToRecipesThenExpectRepositoriesCalled() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(TestObjects.recipeEntity));
        when(userRepository.findById(1L)).thenReturn(Optional.of(TestObjects.userEntity));

        final var expectUser = TestObjects.userEntity.toBuilder().build();
        expectUser.getFavoriteRecipes().add(
                TestObjects.recipeEntity
        );
        userService.addRecipeToFavorites(1L, 1L);
        verify(userRepository).save(expectUser);
    }

    @Test
    void whenRemoveFromRecipesThenExpectRepositoriesCalled() {
        final var userEntityWithFavoriteRecipe = TestObjects.userEntity.toBuilder()
                .build();
        userEntityWithFavoriteRecipe.getFavoriteRecipes().add(TestObjects.recipeEntity);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityWithFavoriteRecipe));
        userService.removeFromFavorites(1L, 1L);
        verify(userRepository).save(TestObjects.userEntity);

    }
}
