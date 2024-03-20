package ray.playground.techcaseabnrt.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ray.playground.techcaseabnrt.domain.models.DishType;
import ray.playground.techcaseabnrt.exceptions.ResourceNotFoundException;
import ray.playground.techcaseabnrt.mappers.DishTypeMapper;
import ray.playground.techcaseabnrt.mappers.RecipeIngredientMapper;
import ray.playground.techcaseabnrt.mappers.RecipeMapper;
import ray.playground.techcaseabnrt.persistence.repositories.RecipeRepository;
import ray.playground.techcaseabnrt.util.TestObjects;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeMapper mapper;

    @Mock
    private DishTypeMapper dishTypeMapper;

    @Mock
    private RecipeIngredientMapper recipeIngredientMapper;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService service;

    @Test
    void whenCreateRecipeThenExpectRecipe() {
        when(mapper.domainToEntity(any())).thenReturn(TestObjects.recipeEntity);
        when(mapper.entityToDomain(any())).thenReturn(TestObjects.recipe);
        when(recipeRepository.save(any())).thenReturn(TestObjects.recipeEntity);
        final var result = service.createRecipe(TestObjects.recipe);
        assertThat(result).isEqualTo(TestObjects.recipe);
    }

    @Test
    void whenDeleteRecipeThenExpectRepositoryCalled() {
        service.deleteRecipeById(1L);
        verify(recipeRepository).deleteById(1L);
    }

    @Test
    void whenGetByRecipeIdThenExpectRecipe() {
        when(mapper.entityToDomain(any())).thenReturn(TestObjects.recipe);
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(TestObjects.recipeEntity));
        final var result = service.getRecipeById(1L);
        assertThat(result).isEqualTo(TestObjects.recipe);
    }

    @Test
    void whenGetByInvalidRecipeIdExpectException() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getRecipeById(1L));

    }

    @Test
    void whenUpdateThenExpectRecipe() {
        when(mapper.domainToEntity(any())).thenReturn(TestObjects.recipeEntity);
        when(mapper.entityToDomain(any())).thenReturn(TestObjects.recipe);
        final var result = service.updateRecipe(TestObjects.recipe);
        assertThat(result).isEqualTo(TestObjects.recipe);
    }

    @Test
    void whenGetRecipesThenExpectRecipes() {
        when(recipeIngredientMapper.domainToEntities(anyList())).thenReturn(List.of());
        when(mapper.entitiesToDomain(anyList())).thenReturn(List.of(TestObjects.recipe));
        when(recipeRepository.findRecipeEntitiesByDishTypeAndServingsAndIngredients(any(), anyInt(),  anyList()))
                .thenReturn(List.of(TestObjects.recipeEntity));

        final var result = service.getRecipes(DishType.VEGAN, 1, "hank", List.of());
        assertThat(result).contains(TestObjects.recipe);
    }
}
