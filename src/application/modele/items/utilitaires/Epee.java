package application.modele.items.utilitaires;

public class Epee extends Utilitaire {
	public static int compteur = 1;
	
	public Epee(int mat) {
		super(mat, "E" + compteur);
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
			this.setDegats(5);
			break;
		}
	}

}
