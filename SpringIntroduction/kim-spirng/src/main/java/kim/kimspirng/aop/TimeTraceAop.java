package kim.kimspirng.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    //aop라는 공통 관심사를 원하는 조건에 등록
    @Around("execution(* kim.kimspirng..*(..))")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + jointPoint.toString());

        try {
            Object result = jointPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + jointPoint.toString() + " " + timeMs + "ms");
        }

    }
}
