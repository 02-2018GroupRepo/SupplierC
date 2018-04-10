package bootcamp.model.invoice;

import java.util.List;

public class Invoice {
	
	private int id;
	private List<InvoiceItem> items;
	private double total = 0d;
	private int quantity;

	public Invoice() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void addItem(InvoiceItem item) {
		items.add(item);
	}
	
	public void removeItem(InvoiceItem item) {
		items.remove(item);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}