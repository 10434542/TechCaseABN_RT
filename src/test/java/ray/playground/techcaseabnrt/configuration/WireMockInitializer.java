package ray.playground.techcaseabnrt.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static int PORT = 8069;
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final var wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        System.out.println("starting wew");
        applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);
        applicationContext.addApplicationListener(listener -> {
            if (listener instanceof ContextClosedEvent || listener instanceof ContextStoppedEvent) {
                wireMockServer.stop();
            }
        });
    }
}
