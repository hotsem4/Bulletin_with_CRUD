package com.bumsu.bulletin_with_crud.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // date같은 것 자동 생성 역할 어노테이션
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("bumsu");  // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자.
    }
}
