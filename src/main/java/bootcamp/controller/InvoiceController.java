package bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import bootcamp.service.InvoiceService;

public class InvoiceController {

	@Autowired
	InvoiceService invoiceService = new InvoiceService();
	
	@RequestMapping("/operatingcash")
	public double getOperatingCash() {
		return invoiceService.getOperatingCash();
	}
}
