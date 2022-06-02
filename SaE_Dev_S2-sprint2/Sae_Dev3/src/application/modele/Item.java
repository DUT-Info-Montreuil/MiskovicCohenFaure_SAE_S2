package application.modele;

public abstract class Item {
	private String id;
	
	public Item(String id) {
		 this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
