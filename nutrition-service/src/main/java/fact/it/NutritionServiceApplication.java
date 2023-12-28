package fact.it;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NutritionServiceApplication  {

    private static final Logger logger = LoggerFactory.getLogger(NutritionServiceApplication.class);

    @Autowired
    private Environment environment;

    @Value("${spring.datasource.url}")
    private String test;
    @PostConstruct
    public void init() {
        for (int i = 0; i < 50; i++) {
            logger.info("????");
            System.out.print("AYOOO " + test);
            System.out.print("k");
        }
        for (int i = 0; i < 10; i++) {
            logger.info("HOST " + environment.getProperty("MYSQL_DB_HOST"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NutritionServiceApplication.class, args);
    }
}
