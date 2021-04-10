package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;

    //주문 상품 생성
    public static OrderItem createOrderItem(Item item,int orderPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //상품
        orderItem.setOrderPrice(orderPrice);   //상품 가격
        orderItem.setCount(count);  //이 상품 몇개 주문할건지

        item.removeStock(count);    //재고에서 빼기
        return orderItem;
    }

    //이 상품 주문 취소
    public void cancel(){
        getItem().addStock(count);  //주문 취소했으니 재고를 다시 복구시켜주기
    }

    //이 상품 총 주문 가격(==이 상품 가격*주문 갯수)
    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", orderPrice=" + orderPrice +
                ", count=" + count +
                '}';
    }
}
