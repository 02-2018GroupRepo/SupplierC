package bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceService {

	@Autowired
	double cashOnHand = 5000d;
	
	public double getOperatingCash() {
		return cashOnHand;
	}

	//take in money
	
	
	// pay for stuff
	
	
	// 
	
	

}
