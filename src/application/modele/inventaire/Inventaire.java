package application.modele.inventaire;

import java.util.ArrayList;

import application.modele.items.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inventaire {
	ArrayList<ListeCaseInventaire> items;
	IntegerProperty indexProperty;
	
	public Inventaire() {
		items = new ArrayList<>();
		indexProperty = new SimpleIntegerProperty(0);
		
		//Il y a 5 emplacements par défaut dans l'inventaire
		for (int i = 0; i < 5; i++) {
			items.add(new ListeCaseInventaire());
		}
		
	}
	
	public void ajouterItem(Item item) {
		switch (item.getClass().getSimpleName()) {
		case "ArmeCaC":
			items.get(0).add(item);
			break;
		case "Pioche":
			items.get(1).add(item);
			break;
		case "Hache":
			items.get(2).add(item);
			break;
		case "ArmeDistance":
			items.get(3).add(item);
			break;
		case "Bloc":
			items.get(4).add(item);
			break;
		}
	}
	
	public Item itemEnMain() {
		return items.get(getIndex()).itemIndex();
	}
	
	public ListeCaseInventaire listItemsIndex() {
		return items.get(getIndex());
	}
	
	public void setIndexProperty(int valeur) {
		indexProperty.set(valeur);
	}
	
	public int taille() {
		return items.size();
	}
	
	public IntegerProperty getIndexProperty() {
		return indexProperty;
	}
	
	public int getIndex() {
		return indexProperty.get();
	}
	
	
	
}
