package application.modele;

public abstract class Mob extends Personnage{

	private Joueur joueur;

	public Mob(int coordX, int coordY, Joueur joueur, Environnement env) {
		super(coordX, coordY,3, env);
		this.joueur = joueur;
	}

	public abstract void detectionJoueur(int temps);
	public abstract void deplacement(int dirGauche, int dirDroite, int temps);
	public abstract void attaque(); // FAIRE SYSTEME DE HITBOX
	
	//Getter
	public Joueur getJoueur() {
		return joueur;
	}
	
}


	




