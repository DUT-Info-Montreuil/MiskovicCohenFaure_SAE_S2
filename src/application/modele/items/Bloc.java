package application.modele.items;

public class Bloc extends Item{
	
	public static int quantite;;
	
	public Bloc(int materiau) {
		super("B" + materiau);
		quantite = 0;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void ajouterQuantite() {
		quantite ++;
	}
	
	public boolean enleverBloc() {
		if (quantite > 0) {
			quantite--;
			return true;
		}
		return false;
	}

}
