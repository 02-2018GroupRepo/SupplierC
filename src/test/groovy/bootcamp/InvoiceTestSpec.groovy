package bootcamp

import bootcamp.model.invoice.Invoice
import bootcamp.model.order.Order
import bootcamp.service.InvoiceService
import spock.lang.Specification

class InvoiceTestSpec extends Specification {
	
	def "create an invoice from order"() {
	given: "an Order"
	Order order = new Order();
	order.setId(999);
	order.setQuantity(99);
	and: "an invoice service"
	InvoiceService invoiceService = new InvoiceService();
	
	when: "an order is placed"
	Invoice invoice = invoiceService.order(order);
	
	then: "an invoice is created"
	invoice.getId() == 999;
	
	}
}