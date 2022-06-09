package application.modele.items;

public class Bloc extends Item{
	
	private int quantite;;
	
	public Bloc(int materiau) {
		super("B" + materiau);
		quantite = 1;
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
