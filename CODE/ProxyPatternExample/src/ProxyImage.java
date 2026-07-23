public class ProxyImage
        implements Image {

    private RealImage image;
    private String file;

    public ProxyImage(String file) {
        this.file = file;
    }

    public void display() {

        if(image == null)
            image =
              new RealImage(file);

        image.display();
    }
}