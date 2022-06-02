package application.modele;

import java.util.ArrayList;

public class ListeCaseInventaire {
	private ArrayList<Item> items;
	private int index;

	public ListeCaseInventaire() {
		items = new ArrayList<Item>();
		items.add(null);
		index = 0;
	}

	public void add(Item item) {
		items.add(item);
	}
	
	public int nombreItems() {
		return items.size();
	}

	public boolean estVide() {
		return items.isEmpty();
	}

	public boolean augmenterIndex() {
		if (index < items.size() - 1) {
			index ++;
			System.out.println(index);
			return true;
		}
		return false;
	}

	public boolean baisserIndex() {
		if (index  > 0) {
			index--;
			System.out.println(index);
			return true;
		}
		return false;
	}

	public Item itemIndex() {
		if (this.estVide())
			return null;
		return items.get(index);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int valeur) {
		index = valeur;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

}
