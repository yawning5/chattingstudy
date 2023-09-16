package com.chatting.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {

    /**
     * redis pub/sub 메시지를 처리하는 listener 설정
     */
    @Bean
    // RedisConnectionFactory: Redis와의 연결을 관리하는 인터페이스, 이를 통해 Redis 연결을 얻을 수 있다.
    // 이 메서드의 매개변수로 주입받아(인자로 받아온다) RedisMessageListnerContainer에 설정한다
    // setConnectionFactory: RedisMessageListenerContainer에 RedisConnectionFactory를 설정하여 Redis와의 연결 확림
    public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    /**
     * 어플리케이션에서 사용할 redisTemplate 설정
     * 역할: Redis 데이터 접근을 위한 중심적인 클래스인 RedisTemplate을 생성하고 구성하는 메서드
     * 해당 구성을 통해서 Redis의 pub/sub 메시징 및 일반적인 데이터 저장/조회를 위한 기본설정이 제공 됨
     */
    @Bean
    // 위에 설명한 코드는 생략하겠음
    // setKeySerializer & setValueSerializer: Redis데이터의 직렬화 및 역직렬화 방식을 설정한다.
    // 이 코드에서 키는 문자열로, 값은 JSON형식의 문자열로 저장된다.
    // StringRedisSerializer는 문자열 데이터 직렬화
    // Jackson2JsonRedisSerializer는 JSON 형태의 데이터를 직렬화하기 위한 시리얼라이저
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate;
    }
}
