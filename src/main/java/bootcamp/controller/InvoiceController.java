package bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import bootcamp.service.InventoryService;
import bootcamp.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService = new InvoiceService();
	@Autowired
	InventoryService inventoryService = new InventoryService();
	
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
	public void receiveInvoice(Invoice invoice) {
		invoiceService.processInvoice(invoice);
	}
	
	// get the money
	@RequestMapping("/pay")
	public void pay(double cash) {
		invoiceService.pay(cash);
	}
	
	// get an order from vendor
	@RequestMapping("/order")
	@ResponseBody
	public Invoice order(@RequestBody Order order) {
		return invoiceService.order(order);
	}
	
}
