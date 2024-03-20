package ray.playground.techcaseabnrt.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ray.playground.techcaseabnrt.exceptions.BadRequestException;
import ray.playground.techcaseabnrt.exceptions.ResourceNotFoundException;
import ray.playground.techcaseabnrt.persistence.repositories.RecipeRepository;
import ray.playground.techcaseabnrt.persistence.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void addRecipeToFavorites(Long recipeId, Long userId) {
        final var recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("no recipe found with id %s", recipeId)));
        final var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("no user found with id %s", userId)));
        user.getFavoriteRecipes().add(recipe);
        userRepository.save(user);
    }

    @Transactional
    public void removeFromFavorites(Long recipeId, Long userId) {
        final var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("no user found with id %s", userId)));
        user.getFavoriteRecipes().stream().filter(recipeEntity -> recipeEntity.getId().equals(userId)).findFirst().orElseThrow(() -> new BadRequestException(String.format("no favorite recipe found with id %s", recipeId)));
        userRepository.save(user);
    }
}
