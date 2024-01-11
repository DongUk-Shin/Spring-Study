package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
        //return price; 지역변수를 반환하면 해결
    }

    public int getPrice() {
        return price;
    }
}
