package application.modele.pnjs;

import application.modele.Environnement;
import application.modele.Personnage;

public abstract class Pnj extends Personnage{

	public Pnj(int coordX, int coordY, Environnement e, int l, int h) {
		super(coordX, coordY, 0, e, l, h);
	}
	
	public abstract void action();

	public void déplacement() {
		
	}
}
