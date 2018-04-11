package bootcamp.dao;

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
	private final String GET_QUAN_INVENTORY = "SELECT number_available FROM inventory";
	private final String INSERT_INVENTORY = "INSERT INTO inventory VALUES (?,?)";
	private final String UPDATE_INVENTORY = "UPDATE inventory SET number_available=?";
	private final String WHERE_INVENTORY = " WHERE id=?";

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
			List<Integer> temp = new ArrayList<>();
			int quantity = 0;
			
			if(check.contains(products.get(i).getId())) {
				for(int j = 0; j < invoice.getItems().size(); j++) {
					if(products.get(i).getId() == invoice.getItems().get(j).getProduct().getId()) {
						temp = jdbcTemplate.queryForList(GET_QUAN_INVENTORY + WHERE_INVENTORY, new Object[] {products.get(i).getId()}, Integer.class);
						quantity = invoice.getItems().get(j).getCount() + temp.get(0);
						break;
					}
				}
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
	
	public int getProductQuantity(int productId) {
		return jdbcTemplate.queryForObject(GET_QUAN_INVENTORY + WHERE_INVENTORY, 
				new Object[] {productId}, Integer.class);
	}
	
	public void setProductQuantity(int id, int count) {
		jdbcTemplate.update(UPDATE_INVENTORY + WHERE_INVENTORY, new Object[] {count, id});
	}
	
}
