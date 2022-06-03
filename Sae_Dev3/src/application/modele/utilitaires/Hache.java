package application.modele.utilitaires;

public class Hache extends Utilitaire {
	public static int compteur = 1;
	public Hache(int mat) {
		super(mat, "H" + compteur);
		compteur++;
		// TODO Auto-generated constructor stub
	}

}
