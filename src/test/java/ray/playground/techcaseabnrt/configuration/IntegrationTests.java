package ray.playground.techcaseabnrt.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ray.playground.techcaseabnrt.mappers.DishTypeMapper;
import ray.playground.techcaseabnrt.mappers.RecipeIngredientMapper;
import ray.playground.techcaseabnrt.mappers.RecipeMapper;
import ray.playground.techcaseabnrt.services.RecipeService;
import ray.playground.techcaseabnrt.services.UserService;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EnableAutoConfiguration
@FlywayTest
@ContextConfiguration(initializers = {WireMockInitializer.class})
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class IntegrationTests {

    @MockBean
    protected UserService userService;

    @Autowired
    protected MockMvc subject;

    @Autowired
    protected WireMockServer wireMockServer;

    @MockBean
    protected RecipeMapper recipeMapper;

    @MockBean
    protected RecipeIngredientMapper recipeIngredientMapper;

    @MockBean
    protected DishTypeMapper dishTypeMapper;

    @MockBean
    protected RecipeService recipeService;

    @BeforeEach
    void resetWireMockServer() {
        wireMockServer.resetAll();
    }
}
