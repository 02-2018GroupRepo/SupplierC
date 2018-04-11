package bootcamp.model.payment;

public class Payment {
	
	private double payment;
	private int id;

	public Payment(double payment) {
		this.payment = payment;
		id = 001;
	}
	
	public Payment(double payment, int id) {
		this.payment = payment;
		this.id = id;
	}
	
	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
