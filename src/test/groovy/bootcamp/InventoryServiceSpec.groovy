package bootcamp

import bootcamp.dao.InventoryDao
import bootcamp.model.inventory.Inventory
import bootcamp.model.products.Product
import bootcamp.service.InventoryService
import spock.lang.Specification


class InventoryServiceSpec extends Specification {
	
	
	
	
	
	
//	def "inventory Dao contians data"(){
//		
//		given: "and inventory Dao"
//		InventoryDao dao = new InventoryDao()
//		
//		when: "the inventory value is requested"
//		 List<Inventory> list = dao.getInventory()
//		
//		then: "return to me a size greater than 0"
//		list.size() > 0;
//		
//		
//	}
	
	
	
	def "Adding a Product List to the Inventory List"(){
		
		given: "An InventoryService"
		InventoryService inventoryService = new InventoryService()
		
		and: "an empty inventory list"
		inventoryService.getInventory().getItems().size() == 0;
		
		and: "A list of 1 product"
		List<Product> productList = new ArrayList<>();
		Product p1 = new Product();
		productList.add(p1);
		
		when: "inventory is received"
		inventoryService.receiveInventory(productList);
		
		then: "The inventory list count should be 1"
		inventoryService.getInventory().getItems().size() == 1;
		
		and: "The inventorylist should contain the product p1"
		inventoryService.getInventory().getItems().contains(p1) == true;
				
	}
	
   

}
