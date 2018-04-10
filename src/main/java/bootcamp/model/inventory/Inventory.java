package bootcamp.model.inventory;

import java.util.List;

public class Inventory {
	
	int id;
	List<InventoryItem> items;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<InventoryItem> getItems() {
		return items;
	}
	public void setItems(List<InventoryItem> items) {
		this.items = items;
	}
	
}
