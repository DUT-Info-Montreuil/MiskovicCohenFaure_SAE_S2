package application.modele;

public class Slime extends Mob{
	
	private int temps;

	public Slime(int coordX, int coordY, Joueur joueur,Environnement e) {
		super(coordX, coordY,e,25,15);
		this.temps=0;
	}

	@Override
	public void detectionJoueur() {
		if (this.getJoueur().getX()-this.getX() < 250 && this.getJoueur().getX()-this.getX() >0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(true);

		if (this.getJoueur().getX()-this.getX() > -250 && this.getJoueur().getX()-this.getX() <0  && this.getJoueur().getY()-this.getY() < 100 && this.getJoueur().getY()-this.getY() > -100) 
			this.deplacement(false);
	}

	public void deplacement(boolean versDroite) {
		temps++;
		if (this.temps==100) {
			if (versDroite) {
				this.setDirDroite(3);
			}
			else {
				this.setDirGauche(3);
			}
			this.setDirY(-7);
			this.temps=0;
		}
	}
	
	public void inertie() {
		if (this.collisionBas()){
			if (this.getDirDroite()>0) {
				this.setDirDroite(this.getDirDroite()-0.2);
			}
			if (this.getDirGauche()>0) {
				this.setDirGauche(this.getDirGauche()-0.2);
			}
		}
	}



}