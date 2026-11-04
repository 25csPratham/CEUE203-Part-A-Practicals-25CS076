import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (Item existing : items) {
            if (existing.getId() == item.getId()) {
                throw new IllegalArgumentException("Item with ID " + item.getId() + " already exists!");
            }
        }
        items.add(item);
    }

    public Item findById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getAll() {
        return new ArrayList<>(items);
    }

    public int getCount() {
        return items.size();
    }
}