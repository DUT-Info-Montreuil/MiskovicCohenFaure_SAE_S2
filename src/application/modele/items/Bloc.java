package application.modele.items;

public class Bloc extends Item{
	
	public static int quantite = 0;
	
	public Bloc(int materiau) {
		super("B" + materiau);
		quantite++;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void ajouterQuantite(int valeur) {
		quantite += valeur;
	}
	
	public boolean enleverBloc() {
		if (quantite > 0) {
			quantite--;
			return true;
		}
		return false;
	}

}
