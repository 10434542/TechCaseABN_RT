package ray.playground.techcaseabnrt.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ray.playground.techcaseabnrt.configuration.IntegrationTests;
import ray.playground.techcaseabnrt.domain.models.DishType;
import ray.playground.techcaseabnrt.util.TestObjects;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@DisabledInAotMode
public class RecipeControllerIntegrationTest extends IntegrationTests {


    @Test
    void whenCreateRecipeThenExpectRecipe() throws Exception {
        when(recipeService.createRecipe(any())).thenReturn(TestObjects.recipe);
        when(recipeMapper.webToDomain(TestObjects.webRecipe)).thenReturn(TestObjects.recipe);
        when(recipeMapper.domainToWeb(TestObjects.recipe)).thenReturn(TestObjects.webRecipe);

        subject.perform(MockMvcRequestBuilders.post("/recipes/createRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "id":1,
                                        "name": "yuk",
                                        "ingredients": [
                                            {
                                                "id": 1,
                                                "name":"carrot",
                                                "ingredient": {
                                                    "id":1,
                                                    "name": "carrot"
                                                },
                                                "quantity": {
                                                    "quantityType": "ABSOLUTE",
                                                    "amount":1,
                                                    "units": "none"
                                                }
                                            }
                                        ],
                                        "createdBy":{
                                                    "id":1,
                                                    "name": "hank",
                                                    "favoriteRecipes":[],
                                                    "createdRecipes":[]
                                        },
                                        "dishType": "VEGAN",
                                        "servings": 1,
                                        "instructions": "dont cook it"
                                        
                                    }
                                """.trim())
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yuk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dishType").value("VEGAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.servings").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions").value("dont cook it"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdBy.name").value("hank"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdBy.id").value("1"));

    }

    @Test
    void whenGetByIdThenExpectRecipe() throws Exception {
        when(recipeMapper.domainToWeb(TestObjects.recipe)).thenReturn(TestObjects.webRecipe);
        when(recipeService.getRecipeById(anyLong())).thenReturn(TestObjects.recipe);
        subject.perform(MockMvcRequestBuilders.get("/recipes/getRecipe/1")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yuk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dishType").value("VEGAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.servings").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions").value("dont cook it"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdBy.name").value("hank"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdBy.id").value("1"));
    }

    @Test
    void whenGetRecipesThenExpectRecipes() throws Exception {
        when(dishTypeMapper.webToDomain(any())).thenReturn(DishType.VEGAN);
        when(recipeIngredientMapper.webToDomain(anyList())).thenReturn(List.of());
        when(recipeService.getRecipes(any(), anyInt(), anyString(), any())).thenReturn(List.of(TestObjects.recipe));
        when(recipeMapper.domainToWeb(anyList())).thenReturn(List.of(TestObjects.webRecipe));
        final MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("dishType", "VEGAN");
        requestParams.add("numberOfServings", "1");
        requestParams.add("text", "dont");

        subject.perform(MockMvcRequestBuilders.get("/recipes/getRecipes")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .queryParams(requestParams))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("yuk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dishType").value("VEGAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].servings").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructions").value("dont cook it"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdBy.name").value("hank"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdBy.id").value("1"));

    }

    @Test
    void whenRemoveRecipeThenExpectEmptyAndOk() throws Exception {
        subject.perform(MockMvcRequestBuilders.delete("/recipes/deleteRecipe/1")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenUpdateRecipeThenExpectRecipe() throws Exception {
        subject.perform(MockMvcRequestBuilders.put("/recipes/updateRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "id":1,
                                        "name": "buk",
                                        "ingredients": [
                                            {
                                                "id": 1,
                                                "name":"carrot",
                                                "ingredient": {
                                                    "id":1,
                                                    "name": "carrot"
                                                },
                                                "quantity": {
                                                    "quantityType": "ABSOLUTE",
                                                    "amount":1,
                                                    "units": "none"
                                                }
                                            }
                                        ],
                                        "createdBy":{
                                                    "id":1,
                                                    "name": "hank",
                                                    "favoriteRecipes":[],
                                                    "createdRecipes":[]
                                        },
                                        "dishType": "VEGAN",
                                        "servings": 1,
                                        "instructions": "dont cook it"
                                        
                                    }
                                """.trim())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
