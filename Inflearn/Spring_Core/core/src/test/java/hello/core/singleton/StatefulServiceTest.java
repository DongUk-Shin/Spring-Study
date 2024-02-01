package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

//무상태
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class); //같은 객체이다
        
        //ThreadA: A사용자 10000원 주문
        bean1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        bean2.order("userB", 20000);

        //ThreadA: 사용자 A가 주문 금액 조회
        int price = bean1.getPrice();
        System.out.println("price = " + price); //2만원 출력

        Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);

        //getPrice를 사용하지 말고 order에서 바로 지역변수를 반환하도록 한다
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}