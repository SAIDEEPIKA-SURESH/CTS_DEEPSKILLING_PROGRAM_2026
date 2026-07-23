public class BuilderTest {

    public static void main(String[] args) {

        Computer c =
            new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM(16)
                .setStorage(512)
                .build();

        c.display();
    }
}