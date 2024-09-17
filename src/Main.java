import model.*;
public class Main {
    public static void main(String[] args) {
        String message = "Hello, World!";

        DataSource dataSource = new FileDataSource("OutputDemo.txt");
        DataSource encrypted = new EncryptionDecorator(dataSource);
        DataSource compressed = new CompressionDecorator(encrypted);

        compressed.writeData(message);
        System.out.println("Data read: " + compressed.readData());
    }
}