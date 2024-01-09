package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //private 생성자로 인해 외부에서 임의로 객체 생성 불가
    private SingletonService() { }

    public void logic() {
        System.out.println("싱글톤 객체 로직 출력");
    }
}
