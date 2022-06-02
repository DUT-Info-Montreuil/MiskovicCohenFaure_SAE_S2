package application.modele;

public class Slime extends Mob{

	public Slime(int coordX, int coordY, Joueur joueur) {
		super(coordX, coordY, joueur);
	}

	@Override
	public void detectionJoueur(Environnement e, int temps) {
		if (this.getJoueur().getX()-this.getX() < 250 && this.getJoueur().getX()-this.getX() >0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(0, 2, temps, e);

		else if (this.getJoueur().getX()-this.getX() > -250 && this.getJoueur().getX()-this.getX() <0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(2, 0, temps, e);
	}

	@Override
	public void deplacement(int dirGauche, int dirDroite, int temps, Environnement e) {
		if (this.collisionBas(e, this.getX(), this.getY()))  {
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
