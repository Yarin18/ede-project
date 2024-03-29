package fact.it.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    /**
     * The redis configuration .
     */
    @Bean
    public RedisStandaloneConfiguration redisConfiguration() {
        return new RedisStandaloneConfiguration("redis", 6379);
    }

    /**
     * The method to create a LettuceConnectionFactory based of the redisConfig.
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisConfiguration());
    }

    /**
     * Fully configured RedisTemplate.
     */
    @Bean
    public RedisTemplate<String, Object> template() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }
}
