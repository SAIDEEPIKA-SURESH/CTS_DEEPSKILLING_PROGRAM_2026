public class RealImage
        implements Image {

    private String file;

    public RealImage(String file) {
        this.file = file;
        load();
    }

    private void load() {
        System.out.println(
            "Loading "+file);
    }

    public void display() {
        System.out.println(
            "Displaying "+file);
    }
}