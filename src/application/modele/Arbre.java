package application.modele;

public class Arbre extends Personnage{

	public Arbre(double coordX, double coordY, Environnement e) {
		super(coordX, coordY, 10, e, 120, 30, 86, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void action() {
		super.action();
		if (this.getPv()<=0) {
			this.getEnv().retirerArbre(this);
		}
	}

}
