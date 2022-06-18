package application.modele.items.utilitaires;


import application.modele.items.Item;



public class Utilitaire extends Item{
	//0: Pierre 1: Fer 2: Or 3: Diamant
	private int materiaux;
	private int degats;
	
	public Utilitaire (int mat, String id) {
		super(id);
		materiaux = mat;
		this.degats = 0;
	}
	
	public int getMateriaux() {
		return materiaux;
	}
	
	public int getDegats() {
		return degats;
	}
	public void setDegats(int valeur) {
		degats = valeur;
	}
}
