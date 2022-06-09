package application.modele;

import java.util.ArrayList;

import application.modele.items.Bloc;
import application.modele.items.Item;

public class ListeCaseInventaire {
	private ArrayList<Item> items;
	private int index;

	public ListeCaseInventaire() {
		items = new ArrayList<Item>();
		items.add(null);
		index = 0;
	}

	public void add(Item item) {
		if (item.getClass().getSimpleName().equals("Bloc")) {
			
			if (this.contient(item)) {
				this.ajouterQuantiteBloc((Bloc) item);
				System.out.println("test");
			}
			else
				items.add(item);
		}
		else
			items.add(item);
	}
	
	public void del(Item item) {
		if (!this.estVide())
			items.remove(items.indexOf(item));
	}
	
	public int nombreItems() {
		return items.size();
	}
	
	//méthodes destinés aux blocs
	public void ajouterQuantiteBloc(Bloc bloc) {
		bloc.ajouterQuantite(1);
	}
	
	
	public boolean contient(Item item) {
		for (int i = 1; i < items.size(); i++)
			if (items.get(i).getId().equals(item.getId())) {
				return true;
			}
		return false;
	}

	public boolean estVide() {
		return items.isEmpty();
	}

	public boolean augmenterIndex() {
		if (index < items.size() - 1) {
			index ++;
			return true;
		}
		return false;
	}

	public boolean baisserIndex() {
		if (index  > 0) {
			index--;
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
