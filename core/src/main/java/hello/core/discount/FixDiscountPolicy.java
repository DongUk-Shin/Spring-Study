package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //동일한 타입의 빈 두개 등록
//@Qualifier("fixDiscountPolicy") 빈 별칭 -> 컴파일시 타입 체크가 안됨
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //천원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        else {
            return 0;
        }
    }
}
