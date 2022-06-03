package application.modele.utilitaires;

public class Arc extends Utilitaire {
	public static int compteur = 1;
	public Arc(int mat) {
		super(mat, "A" + compteur);
		compteur++;
		// TODO Auto-generated constructor stub
	}

}
