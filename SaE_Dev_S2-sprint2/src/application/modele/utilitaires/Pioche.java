package application.modele.utilitaires;

public class Pioche extends Utilitaire {
	public static int compteur = 1;
	public Pioche(int mat) {
		super(mat, "P" + compteur);
		compteur++;
	}

}
