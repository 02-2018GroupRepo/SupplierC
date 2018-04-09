package bootcamp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bootcamp.model.products.Product;

@Component
public class InventoryService {
	
	@Autowired
	private List<Product> inventoryList;
	private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
	private static final BigDecimal retailMultiplier = new BigDecimal(2.5); 
		
	 @Autowired
	 private SimpleDateFormat dateFormat;
	
	public void receiveInventory(List<Product> products) {
		/* 
		//set retail price
		for (Product p : products) {
			p.setRetail_price(p.getWholesale_price().multiply(retailMultiplier));
		}
		*/
		inventoryList.addAll(products);
	}

	public List<Product> getInventory(){
		return inventoryList;
	}
	
	@Scheduled(cron = "${inventory.status.schedule}")
    public void inventoryStatus() {
        log.info("Checking on inventory status at {}", dateFormat.format(new Date()));
        log.debug("Debug goes here");
    }
}
