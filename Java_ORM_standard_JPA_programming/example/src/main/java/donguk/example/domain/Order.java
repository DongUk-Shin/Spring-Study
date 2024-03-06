package donguk.example.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
//@Setter 지양, 편의 메서드 추가할 것 이기에
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    
    @Column(name = "MEMBER_ID")
    private Long memberID;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    //6장 시작
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") //생략 가능한가?
    private Member member;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public void setMember(Member member) { //연관관계 편의 메서드
        if (this.member != null) {
            List<Order> temp = this.member.getOrders();
            temp.remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }
    
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    
    
    //이하는 설정자
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
