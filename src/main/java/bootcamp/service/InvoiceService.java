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
	
	@Bean
	public double[] getOperatingCash() {
		return new double[] {cashOnHand,0d};
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
	
	

}
