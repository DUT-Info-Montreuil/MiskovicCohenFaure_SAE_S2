package application.modele;

import java.util.ArrayList;

import application.modele.items.Bloc;
import application.modele.items.Item;

public class ListeCaseInventaire {
	
	private ArrayList<Item> items;
	
	//Item que le joueur à selectionné
	private int index;
	//Item le plus amélioré que le joueur peut selectionner
	private int indexMax;
	
	//0:Main_vide 1:Pierre 2:Fer 3:Or 4:Diamant

	public ListeCaseInventaire() {
		items = new ArrayList<Item>();
		items.add(null);
		index = 0;
		indexMax=1;
	}

	//AJOUTER & RETIRER ITEM
	public void add(Item item) {
		if (item.getClass().getSimpleName().equals("Bloc")) {
			if (this.contient(item.getId())) {
				this.ajouterQuantiteBloc(item.getId());
			}
			else {
				items.add(item);
				this.augmenterIndexMax();
			}
		}
		else
			items.add(item);
	}
	
	public void del(String idItem) {
		if (!this.estVide())
			items.remove(items.indexOf(this.getItemViaId(idItem)));
	}
	
	public int nombreItems() {
		return items.size();
	}
	
	//GESTION INDEX (Et indexMax)
	public boolean augmenterIndex() {
		if (index < indexMax && index < items.size()-1) {
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
	
	public void augmenterIndexMax() {
		this.indexMax++;
	}
	
	//BLOCs
	public void ajouterQuantiteBloc(String id) {
		Bloc b = (Bloc) this.getItemViaId(id);
		b.ajouterQuantite();
	}
	
	public boolean baisserQuantiteBloc(String id) {
		Bloc b = (Bloc) this.getItemViaId(id);
		b.enleverBloc();
		if (b.getQuantite() == 0) {
			this.del(id);
			return false;
		}
		return true;
	}
	
	//INFORMATION SUR INVENTAIRE
	public boolean contient(String id) {
		for (int i = 1; i < items.size(); i++)
			if (items.get(i).getId().equals(id)) {
				return true;
			}
		return false;
	}

	public boolean estVide() {
		return items.isEmpty();
	}

	//Outils
	public Item getItemViaId(String id) {
		for (int i = 1; i < items.size(); i++) {
			if (items.get(i).getId().equals(id)) {
				return items.get(i);
			}
		}
		return null;
	}
	
	public Item itemIndex() {
		if (this.estVide())
			return null;
		return items.get(index);
	}
	
	//Getters & Setters
	public int getIndexMax() {
		return indexMax;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setIndex(int valeur) {
		index = valeur;
	}
	public int getIndex() {
		return index;
	}
}
