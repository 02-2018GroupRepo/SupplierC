package bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.model.invoice.Invoice;
import bootcamp.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService = new InvoiceService();
	
	// for finance
	@RequestMapping("/operatingcash")
	public double[] getOperatingCash() {
		return invoiceService.getOperatingCash();
	}
	
	// for the resupplier
	@RequestMapping("/invoice/receive")
	public void receiveInvoice(Invoice invoice) {
		invoiceService.processInvoice(invoice);
	}
	
	// get the money
	@RequestMapping("/pay")
	public void pay(double cash) {
		invoiceService.pay(cash);
	}
	
	
}
