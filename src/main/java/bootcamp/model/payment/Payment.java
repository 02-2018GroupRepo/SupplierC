package bootcamp.model.payment;

import java.math.BigDecimal;

public class Payment {
	
	private BigDecimal payment;
	private int id;

	public Payment() {}
	
	public Payment(BigDecimal payment) {
		this.payment = payment;
		id = 001;
	}
	
	public Payment(BigDecimal payment, int id) {
		this.payment = payment;
		this.id = id;
	}
	
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
