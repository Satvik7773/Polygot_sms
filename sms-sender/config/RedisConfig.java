package com.example.sms_sender.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.protocol.ProtocolVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisConfig =
                new RedisStandaloneConfiguration("localhost", 6379);


        LettuceClientConfiguration clientConfig =
                LettuceClientConfiguration.builder()
                        // 🔥 FORCE RESP2 (DISABLE HELLO)
                        .clientOptions(ClientOptions.builder()
                                .protocolVersion(ProtocolVersion.RESP2)
                                .build())
                        .build();

        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(
            LettuceConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }
}
