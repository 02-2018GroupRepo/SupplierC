package bootcamp.model.inventory;

import java.util.ArrayList;

public class Inventory {
	
	ArrayList<InventoryItem> inventoryList = new ArrayList<>();
	
	
	
	public void addItemToList(InventoryItem item) {
		inventoryList.add(item);
	}
}
