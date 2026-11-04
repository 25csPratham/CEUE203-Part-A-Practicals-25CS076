package storage;

import model.Product;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductStorage {
    @SuppressWarnings("unchecked")
    public static void save(List<Product> list, Path file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(new ArrayList<>(list));
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Product> load(Path file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            return (List<Product>) ois.readObject();
        }
    }
}