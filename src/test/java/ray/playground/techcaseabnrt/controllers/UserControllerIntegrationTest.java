package ray.playground.techcaseabnrt.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ray.playground.techcaseabnrt.configuration.IntegrationTests;

@DisabledInAotMode
public class UserControllerIntegrationTest extends IntegrationTests {


    @Test
    void whenAddFavoriteRecipeThenExpectOk() throws Exception {
        subject.perform(MockMvcRequestBuilders.post("/users/addFavoriteRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "userId": 1,
                                        "recipeId": 1
                                    }
                                """.trim())
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void whenDeleteUserThenExpectOk() throws Exception {

        subject.perform(MockMvcRequestBuilders.delete("/admin/removeUser/1")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void whenRemoveFavoriteRecipeThenExpectOk() throws Exception {
        subject.perform(MockMvcRequestBuilders.post("/users/removeFavoriteRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "userId": 1,
                                        "recipeId": 1
                                    }
                                """.trim())
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
