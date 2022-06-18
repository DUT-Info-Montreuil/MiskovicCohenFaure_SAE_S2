package application.modele.items.utilitaires;

public class Hache extends Utilitaire {
	public static int compteur = 1;
	public Hache(int mat) {
		super(mat, "H" + compteur);
		compteur++;
		
		switch (mat) {
		case 0:
			this.setDegats(1);
			break;
		case 1:
			this.setDegats(2);
			break;
		case 2:
			this.setDegats(3);
			break;
		case 3:
			this.setDegats(5);
			break;
		}
	}
	


}
