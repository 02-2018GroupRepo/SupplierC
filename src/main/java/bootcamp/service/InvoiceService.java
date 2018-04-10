package bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import bootcamp.dao.ProductDao;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.invoice.InvoiceItem;
import bootcamp.model.order.Order;

@Component
public class InvoiceService {

	@Autowired
	InventoryService inventoryService;
	@Autowired
	ProductDao productDao;
	
	@Autowired
	double cashOnHand;
	
	@Bean //what annotation goes here to make this happen automatically??
	public double setCashOnHand() {
		return 5000d;
	}
	
	@Bean
	public double[] getOperatingCash() {
		return new double[] {cashOnHand, inventoryService.getInventoryValue()};
	}
	
	
	// process invoice from resupplier, not for vendors
	public void processInvoice(Invoice invoice) {
		for (InvoiceItem i : invoice.getItems()) {
			invoice.setTotal(invoice.getTotal()
					+(i.getCount()*i.getProduct().getRetail_price().longValueExact()));
		}
		if (invoice.getTotal() <= cashOnHand) {
			sendMoney(invoice.getTotal());
		}
		
		else if (invoice.getTotal() > cashOnHand) {
			System.out.println("reject order"); 
			//this is not good enough, come back to this!!
			}
	}

	//take in money from vendors
	public void pay(double newMoney) {
		cashOnHand += newMoney;
	}
	
	// for us to pay resuppliers
	public void sendMoney(double cashGoingOut) {
		cashOnHand -= cashGoingOut;
		// pay the resupplier right here
	}
	
	public Invoice order(Order order) {
		Invoice invoice = new Invoice();
		invoice.setId(order.getId());
		invoice.setQuantity(order.getQuantity());
		invoice.setTotal(productDao.getPriceOfProduct(order.getId()) * order.getQuantity());
		return invoice;
	}
	
	

}
