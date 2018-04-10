package bootcamp.model.inventory;

public class InventoryItem {
	
	private int id;
	private int number_available;
	private double retail_price;
	
	public InventoryItem() {}
	
	public InventoryItem(int id, int number_available, double retail_price) {
		setId(id);
		setNumber_available(number_available);
		setRetail_price(retail_price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber_available() {
		return number_available;
	}

	public void setNumber_available(int number_available) {
		this.number_available = number_available;
	}

	public double getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}

}
