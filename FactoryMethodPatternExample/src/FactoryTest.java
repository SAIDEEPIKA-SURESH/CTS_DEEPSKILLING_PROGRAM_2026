public class FactoryTest {

    public static void main(String[] args) {

        DocumentFactory f1 = new WordFactory();
        f1.createDocument().open();

        DocumentFactory f2 = new PdfFactory();
        f2.createDocument().open();

        DocumentFactory f3 = new ExcelFactory();
        f3.createDocument().open();
    }
}