package application.modele.items.utilitaires;

public class Pioche extends Utilitaire {
	public static int compteur = 1;
	public Pioche(int mat) {
		super(mat, "P" + compteur);
		compteur++;
		
		switch (this.getMateriaux()) {
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
			this.setDegats(4);
			break;
		}
	}

}
