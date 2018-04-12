package bootcamp.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import bootcamp.controller.InvoiceController;
import bootcamp.dao.InventoryDao;
import bootcamp.dao.ProductDao;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.invoice.InvoiceItem;
import bootcamp.model.order.Order;
import bootcamp.model.payment.Payment;

@Component
public class InvoiceService {

	@Autowired
	InventoryService inventoryService;
	@Autowired
	ProductDao productDao;
	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	double cashOnHand;
	@Autowired
	InvoiceController invoiceController;

	HashMap<Integer,Invoice> map = new HashMap<>();
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Bean //what annotation goes here to make this happen automatically??
	public double setCashOnHand() {
		return 5000d;
	}
	
	@Bean
	public double[] getOperatingCash() {
		return new double[] {cashOnHand, inventoryService.getInventoryValue()};
	}
	
	@Bean
	public HashMap<Integer,Invoice> getMap() {
		return map;
	}
	
	// process invoice from resupplier, not for vendors
	public void processInvoice(Invoice invoice) {
		//if (invoice.)
		
		// get invoice total price
		for (InvoiceItem i : invoice.getItems()) {
			invoice.setTotal(invoice.getTotal()
					+(i.getCount()*i.getProduct().getRetail_price().doubleValue()));
		}
		log.info(String.valueOf(invoice.getTotal()));
	
		if (invoice.getTotal() <= cashOnHand) {
			inventoryDao.setInvoice(invoice);
			sendMoney(invoice.getTotal(), invoice.getId());
		}
		
		else if (invoice.getTotal() > cashOnHand) {
			System.out.println("reject order"); 
			//this is not good enough, come back to this!!
			}
	}

	//take in money from vendors
	public Boolean payment(Payment payment) {
		if (payment.getPayment().doubleValue() == map.get(payment.getId()).getTotal()) {
			cashOnHand += payment.getPayment().doubleValue();
			int temp = inventoryDao.getProductQuantity(payment.getId()) - map.get(payment.getId()).getQuantity();
			inventoryDao.setProductQuantity(payment.getId(), temp);
			return new Boolean(true);
		}
		else {
			log.info("Payment from ID " + payment.getId() + "rejected.");
			return new Boolean(false);
		}
			
	}
	
	// for us to pay resuppliers
	public void sendMoney(double cashGoingOut, int id) {
		cashOnHand -= cashGoingOut;
		String uri = "http://192.168.88.75:8080/payment/" + id;
	
		// pay the resupplier right here
		invoiceController.payReSupply(uri, cashGoingOut, id);
	}
	
	public Invoice order(Order order) {
		Invoice invoice = new Invoice();
		invoice.setId(order.getId());
		
		// get quantity from inventory
		if (inventoryDao.getProductQuantity(order.getId()) < order.getQuantity())
			invoice.setQuantity(inventoryDao.getProductQuantity(order.getId()));
		else
			invoice.setQuantity(order.getQuantity());
		
		// get retail price from products
		invoice.setTotal(productDao.getPriceOfProduct(order.getId()) * order.getQuantity());
		
		map.put(invoice.getId(), invoice);
		return invoice;
	}

}
