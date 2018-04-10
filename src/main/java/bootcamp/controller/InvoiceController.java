package bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.model.invoice.Invoice;
import bootcamp.service.InventoryService;
import bootcamp.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService = new InvoiceService();
	@Autowired
	InventoryService inventoryService = new InventoryService();
	
	@RequestMapping("/operatingcash")
	public double getOperatingCash() {
		return invoiceService.getOperatingCash();
	}
	
	
	@RequestMapping("inventory/value")
	public double getInventoryValue() {
		return inventoryService.getInventoryValue();
	}
	
	@RequestMapping("invoice/receive")
	public void receiveInvoice(Invoice invoice) {
		invoiceService.processInvoice(invoice);
	}
}
