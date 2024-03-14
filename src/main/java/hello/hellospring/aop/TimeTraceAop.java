package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP로 사용하기 위한 어노테이션
@Component
public class TimeTraceAop {

    // 적용하는 범위를 정할 수 있다.
//    @Around("execution(* hello.hellospring..*(..))") // 패키지의 모든 곳에 적용
    // 직접 빈으로 등록할 경우 순환 참조를 막기위해서 해당 빈등록이 있는 클래스를 제외 해준다.
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.config.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint + " " + timeMs + "ms");
        }
    }
}
