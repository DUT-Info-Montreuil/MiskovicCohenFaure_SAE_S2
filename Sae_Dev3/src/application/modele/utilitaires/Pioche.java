package application.modele.utilitaires;

public class Pioche extends Utilitaire {
	public static int compteur = 0;
	public Pioche(int mat) {
		super(mat, "P" + compteur);
		compteur++;
		// TODO Auto-generated constructor stub
	}

}
