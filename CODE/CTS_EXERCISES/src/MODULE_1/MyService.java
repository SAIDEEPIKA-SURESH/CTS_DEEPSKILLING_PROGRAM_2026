package MODULE_1;

public class MyService {

    private ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public void save(String data) {
        api.saveData(data);
    }

    public void delete() {
        api.deleteData();
    }

    public void processUser(String name, int age) {
        api.process(name, age);
    }
}