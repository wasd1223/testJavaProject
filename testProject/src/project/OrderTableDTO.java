package project;

public class OrderTableDTO {
	String num, office, tradename, company;
	int sal, amount, exprice;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getExprice() {
		return exprice;
	}
	public void setExprice(int exprice) {
		this.exprice = exprice;
	}
	@Override
	public String toString() {
		return "OrderTableDTO [num=" + num + ", office=" + office + ", tradename=" + tradename + ", company=" + company
				+ ", sal=" + sal + ", amount=" + amount + ", exprice=" + exprice + "]";
	}
	public OrderTableDTO(String num, String office, String tradename, String company, int sal, int amount) {
		super();
		this.num = num;
		this.office = office;
		this.tradename = tradename;
		this.company = company;
		this.sal = sal;
		this.amount = amount;
		exprice = sal * amount;
	}
	public void OrderTableDTO() {
	}
	
	

}
