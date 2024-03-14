package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional // 테스트 데이터를 롤백 해준다.
public class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    @DisplayName("회원 가입")
    void join() {
        // given
        Member member = new Member();
        member.setName("test");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId);
        assertEquals(member.getName(), findMember.getName());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("회원 가입 - 중복 회원 처리")
    public void joinException() {
        // given
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        // when
        memberService.join(member1);

/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("전체 회원 조회")
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("test1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("test2");
        memberService.join(member2);

        // when
        List<Member> memberList = memberService.findMembers();

        // then
        assertEquals(memberList.get(0).getName(), member1.getName());
        assertThat(memberList.get(1).getName()).isEqualTo(member2.getName());
    }

    @Test
    @DisplayName("회원 조회")
    void findOne() {
        // given
        Member member = new Member();
        member.setName("test");
        Long saveId = memberService.join(member);

        // when
        Member findMember = memberService.findOne(saveId);

        // then
        assertEquals(member.getName(), findMember.getName());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
