package application.modele.inventaire;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListeCaseInventaire {
	private ObservableList<Item> items;
	private int index;
	
	public ListeCaseInventaire() {
		items = FXCollections.observableList(new ArrayList<Item>());
		items.add(null);
		index = 0;
	}

	public void add(Item item) {
		items.add(item);
	}
	
	public boolean estVide() {
		return items.isEmpty();
	}
	
	public void augmenterIndex() {
		if (index < items.size()-1)
			index++;
		System.out.println(index);
	}
	
	public void baisserIndex() {
		if (index > 0)
			index--;
		System.out.println(index);
	}
	
	public Item itemIndex() {
		if (this.estVide())
			return null;
		return items.get(index);
	}
	
	public ObservableList<Item> getItems() {
		return items;
	}
}
