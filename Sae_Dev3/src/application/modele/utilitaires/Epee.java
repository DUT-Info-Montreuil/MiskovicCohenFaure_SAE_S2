package application.modele.utilitaires;

public class Epee extends Utilitaire {
	public static int compteur = 1;
	public Epee(int mat) {
		super(mat, "E" + compteur);
		compteur++;
		// TODO Auto-generated constructor stub
	}

}
