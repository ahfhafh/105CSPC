public class Item {
	private String name;
	private double unitPrice;
	private int stock;;
	
	public Item(String name, double price, int stock) {
		this.name = name;
		this.unitPrice = price;
		this.stock = stock;
	}

	public double buyItem(double money) {
		if (OutOfStock(money)) {
			System.out.println(getName() + " is not available.");
			return money;
		}
		
		if (TooLittleMoney(money)) {
			System.out.println("Insufficient funds.");
			return money;
		}
		
		setStock(getStock()-1);
		System.out.println(getName() + " purchased.");
		
		return getChange(money);	
	}

	public boolean OutOfStock(double money) {
		return (getStock() == 0);
	}

	public boolean TooLittleMoney(double money) {
		return (getUnitPrice() > money);
	}

	public double getChange(double money) {
		return (money - getUnitPrice());
	}
	
	public String getName() {return name;}
	public double getUnitPrice() {return unitPrice;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	
}
