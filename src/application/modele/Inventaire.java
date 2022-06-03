package application.modele;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inventaire {
	ArrayList<ListeCaseInventaire> items;
	IntegerProperty indexProperty;
	IntegerProperty indexCaseProperty;
	
	public Inventaire() {
		items = new ArrayList<>();
		indexProperty = new SimpleIntegerProperty(0);
		indexCaseProperty = new SimpleIntegerProperty(0);
		
		
		//Il y a 5 emplacements par défaut dans l'inventaire
		for (int i = 0; i < 5; i++) {
			items.add(new ListeCaseInventaire());
		}
		
	}
	
	public void ajouterItem(Item item) {
		switch (item.getClass().getSimpleName()) {
		case "Epee":
			items.get(0).add(item);
			break;
		case "Pioche":
			items.get(1).add(item);
			break;
		case "Hache":
			items.get(2).add(item);
			break;
		case "Arc":
			items.get(3).add(item);
			break;
		case "Bloc":
			items.get(4).add(item);
			break;
		}
	}
	
	public String idItemEnMain() {
		if (this.itemEnMain() == null) {
			return null;
		}
		return this.itemEnMain().getId();
	}
	
	public Item itemEnMain() {
		return items.get(getIndexProperty()).itemIndex();
	}
	
	public ListeCaseInventaire listItemsIndex() {
		return items.get(getIndexProperty());
	}
	
	public void changerCase(int valeur) {
		this.setIndexProperty(valeur);
		this.setIndexCaseProperty(this.listItemsIndex().getIndex());
	}
	
	public int taille() {
		return items.size();
	}
	
	//Selectionne l'item suivant de la case où on se situe (ex : on passe de pioche en pierre à pioche en fer)
	public void itemSuivant() {
		if (this.listItemsIndex().augmenterIndex()) {
			this.setIndexCaseProperty(this.listItemsIndex().getIndex());
		}
	}
	
	public void itemPrecedent() {
		if (this.listItemsIndex().baisserIndex()) {
			this.setIndexCaseProperty(this.listItemsIndex().getIndex());
		}
	}
	
	public void setIndexProperty(int valeur) {
		indexProperty.set(valeur);
	}
	
	public void setIndexCaseProperty(int valeur) {
		indexCaseProperty.set(valeur);
	}
	
	public IntegerProperty indexProperty() {
		return indexProperty;
	}
	
	public IntegerProperty indexCaseProperty() {
		return indexCaseProperty;
	}
	
	public int getIndexProperty() {
		return indexProperty.get();
	}
	
	
	
}
