package hello.hellospring.config;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // 자바 코드로 직접 스프링 빈 등록

//    private DataSource dataSource;
//    @PersistenceContext // 원래는 추가를 해주어야 한다.
//    private EntityManager em;

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//    public SpringConfig(EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
