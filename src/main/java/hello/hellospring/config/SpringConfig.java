package hello.hellospring.config;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 자바 코드로 직접 스프링 빈 등록

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
