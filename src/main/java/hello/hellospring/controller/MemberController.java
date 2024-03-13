package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 생성자가 하나만 존재할 경우 생략이 가능하다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
