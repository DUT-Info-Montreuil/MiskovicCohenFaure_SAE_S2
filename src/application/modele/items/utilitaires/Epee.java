package application.modele.items.utilitaires;

public class Epee extends Utilitaire {
	public static int compteur = 1;
	
	public Epee(int mat) {
		super(mat, "E" + compteur);
		compteur++;
	}

}
