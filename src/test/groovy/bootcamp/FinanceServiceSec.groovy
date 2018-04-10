package bootcamp

import bootcamp.service.InvoiceService
import spock.lang.Specification

class FinanceServiceSec extends Specification {
	
	def "Check the finance return endpoint"() {
		
		given: "An invoice service"
		InvoiceService invoiceService = new InvoiceService();
		
		when: "the call is made"
		invoiceService.setCashOnHand();
		double[] tester = invoiceService.getOperatingCash();
		
		then: "it should return cash on hand and inventory total"
		tester[0] == 5000;
		tester[1] == 0;
		
	}
	
	
	
}
