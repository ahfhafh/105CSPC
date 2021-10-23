public class Dispenser {
	private Item[] items;
	
	public Dispenser(Item[] items) {
		this.items = items;
	}
	
	public Item getItem(int itemKey) {
		return items[itemKey];
	}
	
}
