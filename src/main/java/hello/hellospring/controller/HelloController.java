package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // attributeName : 변수명, attributeValue : 변수의 값
        model.addAttribute("data", "hello!!");
        // rest Controller가 아닌 일반 controller의 경우 리턴 값으로 문자를 반환하면 viewResolver에서 화면을 찾아 처리한다.
        // viewResolver가 resources/templates에 있는 같은 이름을 가진 html 파일을 viewName매핑을 한다.
        return "hello";
    }
}
