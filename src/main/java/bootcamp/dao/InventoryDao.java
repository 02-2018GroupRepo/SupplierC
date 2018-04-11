package bootcamp.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bootcamp.model.inventory.Inventory;
import bootcamp.model.inventory.InventoryItem;
import bootcamp.model.inventory.InventoryValue;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.products.Product;

@Component
public class InventoryDao {
	
	private final String GET_INVENTORY = "SELECT inventory.id, inventory.number_available, product.retail_price\r\n" + 
			"FROM inventory\r\n" + 
			"JOIN product\r\n" + 
			"ON inventory.id=product.id";
	private final String GET_INVENTORY_VALUE = "SELECT inventory.id, inventory.number_available, product.wholesale_price\r\n" +
			"FROM inventory\r\n" +
			"JOIN product\r\n" +
			"ON inventory.id=product.id";
	private final String GET_ID_INVENTORY = "SELECT id FROM inventory";
	private final String INSERT_INVENTORY = "INSERT INTO inventory VALUES (?,?)";
	private final String UPDATE_INVENTORY = "UPDATE inventory SET number_available=?";
	private final String WHERE_INVENTORY = " WHERE id=?";
	private final String QUANT_INVENTORY = "SELECT number_available FROM inventory WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Invoice invoice = new Invoice();
	
	public Inventory getInventory() {
		List<InventoryItem> inven_item = jdbcTemplate.query(GET_INVENTORY, new BeanPropertyRowMapper<>(InventoryItem.class));
		Inventory inventory = new Inventory();
		inventory.setItems(inven_item);
		return inventory;
	}
	
	public List<InventoryValue> getInventoryValue() {
		List<InventoryValue> inven_value = jdbcTemplate.query(GET_INVENTORY_VALUE, new BeanPropertyRowMapper<>(InventoryValue.class));
		return inven_value;
	}
	
	public void addToInventory(List<Product> products) {
		List<Integer> check = new ArrayList<>();
		for(int i = 0; i < products.size(); i++) {
			check = jdbcTemplate.queryForList(GET_ID_INVENTORY, Integer.class);
			System.out.println(check);
			int quantity = 0;
			
			for(int j = 0; j < invoice.getItems().size(); j++) {
				if(products.get(i).getId() == invoice.getItems().get(i).getProduct().getId())
					quantity = invoice.getItems().get(i).getCount();
			}
			
			
			if(check.contains(products.get(i).getId())) {
				jdbcTemplate.update(UPDATE_INVENTORY 
						+ WHERE_INVENTORY, new Object[] {quantity, products.get(i).getId()});
			} else {
				jdbcTemplate.update(INSERT_INVENTORY, 
						new Object[] {products.get(i).getId(), quantity});
			}
		}
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
