package bootcamp.model.inventory;

public class InventoryValue {
	
	private int id;
	private int number_available;
	private double wholesale_price;
	
	public InventoryValue() {}
	
	public InventoryValue(int id, int number_available, double wholesale_price) {
		this.id = id;
		this.number_available = number_available;
		this.wholesale_price = wholesale_price;
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

	public double getWholesale_price() {
		return wholesale_price;
	}

	public void setWholesale_price(double wholesale_price) {
		this.wholesale_price = wholesale_price;
	}

}
