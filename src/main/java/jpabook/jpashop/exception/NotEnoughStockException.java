package jpabook.jpashop.exception;

/**
 * 재고 부족 예외
 * Item class의 removeStock()메서드에서 사용
 */
@SuppressWarnings("serial")
public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}
