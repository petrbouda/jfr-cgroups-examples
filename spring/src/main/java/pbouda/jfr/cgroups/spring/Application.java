package pbouda.jfr.cgroups.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testcontainers.containers.wait.strategy.Wait;
import pbouda.jfr.cgroups.recorder.CgroupsRecorder;

import java.util.Map;

@EnableMongoRepositories
@SpringBootApplication
public class Application {

    private static class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final MongoDbContainer CONTAINER = new MongoDbContainer()
                .withEnv("discovery.type", "single-node")
                .waitingFor(Wait.forHttp("/").forStatusCode(200));

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            CONTAINER.start();

            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MutablePropertySources propertySources = environment.getPropertySources();
            Map<String, Object> port = Map.of("spring.data.mongodb.port", CONTAINER.getPort());
            propertySources.addFirst(new MapPropertySource("custom-map", port));
        }
    }

    public static void main(String[] args) {
        CgroupsRecorder.start();

        SpringApplication application = new SpringApplication(Application.class);
        application.addInitializers(new MongoInitializer());
        application.run(args);
    }
}

