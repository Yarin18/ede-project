package fact.it;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NutritionServiceApplication  {

    private static final Logger logger = LoggerFactory.getLogger(NutritionServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NutritionServiceApplication.class);
        application.addInitializers(new CustomContextInitializer());
        application.run(args);
    }

    static class CustomContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            String myProperty = environment.getProperty("MYSQL_DB_HOST");
            logger.info("PROPERTY " + myProperty);
        }
    }

}
