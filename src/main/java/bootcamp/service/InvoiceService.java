package bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import bootcamp.model.invoice.Invoice;
import bootcamp.model.invoice.InvoiceItem;

@Component
public class InvoiceService {

	@Autowired
	double cashOnHand;
	
	@Bean
	public double setCashOnHand() {
		cashOnHand = 5000d;
		return cashOnHand;
	}
	
	public double getOperatingCash() {
		return 5000;
	}
	
	public void processInvoice(Invoice invoice) {
		for (InvoiceItem i : invoice.getItems()) {
			invoice.setTotal(invoice.getTotal()
					+(i.getCount()*i.getProduct().getRetail_price().longValueExact()));
		}
		if (invoice.getTotal() <= cashOnHand)
			pay(invoice.getTotal());
		else if (invoice.getTotal() > cashOnHand)
			System.out.println("reject order"); //this is not good enough, come back to this!!
	}

	//take in money
	public void takeMoney(double newMoney) {
		cashOnHand += newMoney;
	}
	
	// pay for stuff
	public void pay(double owed) {
		cashOnHand -= owed;
	}
	
	

}
