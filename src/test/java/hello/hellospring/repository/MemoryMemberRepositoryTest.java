package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트 하나마다 끝나면 뒤에 작동
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void findById() {
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // junit
        assertEquals(result, member);
        // assertj
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findByName("test1").get();

        assertEquals(result, member1);
        assertThat(result).isNotEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(result.size(), 2);
        assertThat(result.size()).isEqualTo(2);
    }
}
