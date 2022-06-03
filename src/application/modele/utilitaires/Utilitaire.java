package application.modele.utilitaires;


import application.modele.Item;



public class Utilitaire extends Item{
	//0: Pierre 1: Fer 2: Or 3: Diamant
	private int materiaux;
	
	public Utilitaire (int mat, String id) {
		super(id);
		materiaux = mat;
	}
	
	public int getMateriaux() {
		return materiaux;
	}
}
