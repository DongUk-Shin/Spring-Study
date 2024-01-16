package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Test
    void SingletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        /*
        프로토타입 빈을 만들었으나 2가 된다 프로토타입 빈은 공유됨 like static
        싱글톤 빈은 생성 시점에만 DI를 받기 때문에 프로토타입 빈은 유지됨
        이는 의도된 것이 아님 이럴빠엔 싱글톤 쓰지
         */

    }

    @RequiredArgsConstructor
    @Scope("singleton")
    static class ClientBean {
        //private final PrototypeBean prototypeBean; //생성 시점에 주입 됨
        //private final ObjectProvider<PrototypeBean> prototypeBeanProvider; //스프링이 알아서 주입해줌
        private final Provider<PrototypeBean> prototypeBeanProvider; //얘는 자바 표준 기능이 단순하기에 딱 DL만

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); //프로토타입 빈을 찾아준다 -> 생성됨, 대신 찾아주는거
            prototypeBean.addCount();
            int count;
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count;

        private void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
