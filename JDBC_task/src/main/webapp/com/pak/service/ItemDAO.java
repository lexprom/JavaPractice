package main.webapp.com.pak.service;

import main.webapp.com.pak.model.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ItemDAO
{
    private File file;

    private int idCounter;

    private String path = "\\resources\\items.txt";

    public ItemDAO(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(":");
                items.add(new Item(words[0], Integer.parseInt(words[1]), words[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void addItem(Item item) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(item.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
