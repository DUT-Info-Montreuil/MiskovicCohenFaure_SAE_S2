package application.modele.items.utilitaires;

public class Hache extends Utilitaire {
	public static int compteur = 1;
	public Hache(int mat) {
		super(mat, "H" + compteur);
		compteur++;
		
		switch (mat) {
		case 0:
			this.setDegats(1);
		case 1:
			this.setDegats(2);
		case 2:
			this.setDegats(3);
		case 3:
			this.setDegats(5);
		}
	}
	


}
