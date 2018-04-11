package bootcamp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import bootcamp.model.payment.Payment;
import bootcamp.service.InventoryService;
import bootcamp.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService = new InvoiceService();
	@Autowired
	InventoryService inventoryService = new InventoryService();
	
	@Autowired
	RestTemplate restTemplate;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	// for finance
	@RequestMapping("/operatingcash")
	public double[] getOperatingCash() {
		return invoiceService.getOperatingCash();
	}	
	
	
	@RequestMapping("inventory/value")
	public double getInventoryValue() {
		return inventoryService.getInventoryValue();
	}
	
	// for the resupplier
	@RequestMapping("invoice/receive")
	public void receiveInvoice(@RequestBody Invoice invoice) {
		log.info("invoice came in");
		invoiceService.processInvoice(invoice);
	}
	
	// get the money
	@RequestMapping("/payment")
	public void payment(double cash) {
		invoiceService.payment(cash);
	}
	
	// get an order from vendor
	@RequestMapping("/order")
	@ResponseBody
	public Invoice order(@RequestBody Order order) {
		return invoiceService.order(order);
	}


	// pay resupply cause they are jerks
	public void payReSupply(String uri, double cashGoingOut, int id) {
		log.info(uri);
		Payment payment = new Payment(cashGoingOut);
		try {
		restTemplate.postForObject(uri, payment, Payment.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
