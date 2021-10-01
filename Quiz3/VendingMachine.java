public class VendingMachine {
	private String serviceNumber;
	private String location;
	private Dispenser dispenser;
	
	public VendingMachine(String serviceNum, String location) {
		this.serviceNumber = serviceNum;
		this.location = location;
		
		Item[] items = new Item[3];
		items[0] = new Item("Soda", 4.75, 5);
		items[1] = new Item("Water", 4.00, 3);
		items[2] = new Item("Chocolate milk", 5.20, 6);
		
		dispenser = new Dispenser(items);
	}

	public double purchase(int itemKey, double money) {
		Item item = dispenser.getItem(itemKey);
		return item.buyItem(money);
	}

	public static void main(String args[]) {
		System.out.println("hi");

		VendingMachine machine1 = new VendingMachine("1", "Calgary");

		machine1.purchase(1, 2);

	}
		
}