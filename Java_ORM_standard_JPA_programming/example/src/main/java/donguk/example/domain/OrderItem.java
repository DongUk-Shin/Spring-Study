package donguk.example.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    
    @Column(name = "ITEM_ID")
    private Long itemId;
    @Column(name = "ORDER_ID")
    private Long orderId;
    
    private int orderPrice;
    private int count;
    
    
    //6장 추가
    
    @ManyToOne //상품 -> 주문상품을 참조를 안하기에 단방향.
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    
    
}
