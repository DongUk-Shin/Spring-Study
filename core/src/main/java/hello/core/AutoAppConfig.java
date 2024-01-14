package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
컴포넌트 스캔은 @Component가 붙은 클래스들이 등록된다
 */
@Configuration
@ComponentScan(
        /*
        basePackages = "hello.core", 
        basePackageClasses = AutoAppConfig.class,
        스캔 위치 지정 방법
        Default 는 설정 정보 클래스의 위치 -> 설정 정보 클래스를 프로젝트 최상단에 위치하자
         */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //기존 @Configuration이 붙은거 다 제외시킨다
public class AutoAppConfig {

}
