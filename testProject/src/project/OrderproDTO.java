package project;

public class OrderproDTO {
	String tradename;
	int amount, total;
	
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "OrderproDTO [tradename=" + tradename + ", amount=" + amount + ", total=" + total + "]";
	}
	
	public OrderproDTO(String tradename, int amount) {
		super();
		this.tradename = tradename;
		this.amount = amount;
		total = total;
	}
	
}
