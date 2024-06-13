package bitstudy.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//@Configuration -> JPA가 설정파일로 인식 -> Configration Bean으로 등록
@Configuration
//JAP auditing 가능 하게 하는 어노테이션 -> Spring Data Jpa에서 자동으로 값 입력
@EnableJpaAuditing
public class Ex01_3_JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return ()-> Optional.of("leeyoungkyu");
    }
}
