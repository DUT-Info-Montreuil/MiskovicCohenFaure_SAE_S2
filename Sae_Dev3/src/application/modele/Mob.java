package application.modele;

public abstract class Mob extends Personnage{

	private Joueur joueur;

	public Mob(int coordX, int coordY, Joueur joueur) {
		super(coordX, coordY,3);
		this.joueur = joueur;
	}

	public abstract void detectionJoueur(Environnement e, int temps);
	public abstract void deplacement(int dirGauche, int dirDroite, int temps, Environnement e);
	public abstract void attaque(); // FAIRE SYSTEME DE HITBOX
	
	//Getter
	public Joueur getJoueur() {
		return joueur;
	}
	
}


	




