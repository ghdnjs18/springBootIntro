package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-mvc")
    // @RequestParam : get방식에서 ?name=값 형태로 사용되는 기본적인 값 전달 방식
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http에서 body 부분에 값을 직접 넘긴다.
    public String helloString(@RequestParam("name") String name, Model model) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 객체를 자동으로 json형태로 변경하여 전달한다.
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
