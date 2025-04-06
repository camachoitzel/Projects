package Tea;

import java.awt.Dimension;

public class Tea extends TeaWindow {
	
	private int price;
	
	private int sugarLevel;
	
	private Dimension size;
	
	private String customer;
	
	public Tea() {
		
		
	}
	
	public int getPrice() {
		
		return this.price;
		
	}
	
	public int getSugarLevel() {
		
		return this.sugarLevel;
	}
	
	public Dimension getSize() {
		
		return this.size;
	}
	
	public String getCustomer() {
		
		return this.customer;
	}

}
