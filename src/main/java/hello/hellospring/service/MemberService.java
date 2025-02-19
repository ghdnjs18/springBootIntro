package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 의존성 주입으로 사용
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // result.get() 으로 값을 꺼내는 것은 권장하지 않는다.
//        result.ifPresent(m -> { // optional 메소드 활용
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 자주 사용할 것 같은 검증관련 로직은 메서드로 사용하면 편하다.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    private void validateDuplicateMember(Member member) {
        // Optional로 반환 값을 받는 것도 권장하지 않아 바로 사용하는 경우도 있다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
