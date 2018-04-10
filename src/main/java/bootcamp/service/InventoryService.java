package bootcamp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bootcamp.dao.InventoryDao;
import bootcamp.dao.ProductDao;
import bootcamp.model.inventory.Inventory;
import bootcamp.model.inventory.InventoryValue;
import bootcamp.model.products.Product;

@Component
public class InventoryService {

	private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
		
//	@Autowired
//	private List<Product> inventoryList;
//	private static final BigDecimal retailMultiplier = new BigDecimal(2.5); 
		
	@Autowired
	private SimpleDateFormat dateFormat;
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private ProductDao productDao;
	
	public void receiveInventory(List<Product> products) {
		/* 
		//set retail price
		for (Product p : products) {
			p.setRetail_price(p.getWholesale_price().multiply(retailMultiplier));
		}
		*/
		productDao.updateProductPrice(products);
		inventoryDao.addToInventory(products);
	}

	public double getInventoryValue() {
		double inven_value = 0.0;
		List<InventoryValue> inventory = inventoryDao.getInventoryValue();
		for(int i = 0; i < inventory.size(); i++) {
			inven_value += inventory.get(i).getNumber_available() * inventory.get(i).getWholesale_price();
		}
		return inven_value;
	}
	
	public Inventory getInventory() {
		return inventoryDao.getInventory();
	}
	
	@Scheduled(cron = "${inventory.status.schedule}")
    public void inventoryStatus() {
        log.info("Checking on inventory status at {}", dateFormat.format(new Date()));
        log.debug("Debug goes here");
    }
}
