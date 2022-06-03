package application.modele.utilitaires;

<<<<<<< HEAD
import application.modele.Item;
=======
import application.modele.inventaire.Item;
>>>>>>> refs/heads/dev

public class Utilitaire extends Item{
	//0: Pierre 1: Fer 2: Or 3: Diamant
	private int materiaux;
	
	public Utilitaire (int mat, String id) {
		super(id);
		materiaux = mat;
	}
	
	public int getMateriaux() {
		return materiaux;
	}
}
