package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach // 각 테스트 실행 전에 호출
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

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