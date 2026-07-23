public class ProxyTest {

    public static void main(String[] args) {

        Image img =
            new ProxyImage("photo.jpg");

        img.display();
        img.display();
    }
}