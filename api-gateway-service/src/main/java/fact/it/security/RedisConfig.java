package fact.it.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    /**
     * The redis configuration .
     */
    @Bean
    public RedisStandaloneConfiguration redisConfig() {
        return new RedisStandaloneConfiguration("redis", 6379);
    }

    /**
     * The method to create a LettuceConnectionFactory based of the redisConfig.
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisConfig());
    }

    /**
     * Fully configured RedisTemplate.
     */
    @Bean
    public RedisTemplate<String, Object> template() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        return redisTemplate;
    }
}
