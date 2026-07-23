package MODULE_1;

public interface ExternalApi {

    String getData();

    void saveData(String data);

    void deleteData();

    void process(String name, int age);
}