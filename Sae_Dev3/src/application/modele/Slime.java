package application.modele;

public class Slime extends Mob{

	public Slime(int coordX, int coordY, Joueur joueur, Environnement env) {
		super(coordX, coordY, joueur, env);
	}

	@Override
	public void detectionJoueur(int temps) {
		if (this.getJoueur().getX()-this.getX() < 250 && this.getJoueur().getX()-this.getX() >0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(0, 2, temps);

		else if (this.getJoueur().getX()-this.getX() > -250 && this.getJoueur().getX()-this.getX() <0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(2, 0, temps);
	}

	@Override
	public void deplacement(int dirGauche, int dirDroite, int temps) {
		if (this.collisionBas(this.getX(), this.getY()))  {
			this.setDirGauche(0);
			this.setDirDroite(0);
			if (temps%55 == 0) 
				this.additionnerDirY(-10);
		}
		else {
			this.setDirDroite(dirDroite);
			this.setDirGauche(dirGauche);
		}
	}

	@Override
	public void attaque() {
		if (this.getJoueur().getX() == this.getX() && this.getJoueur().getY() == this.getY())
			this.getJoueur().perdrePV(1);
		
	}



}
