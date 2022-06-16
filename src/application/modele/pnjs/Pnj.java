package application.modele.pnjs;

import application.modele.Environnement;
import application.modele.Personnage;

public abstract class Pnj extends Personnage{

	public Pnj(double coordX, double coordY, int pvMax,Environnement e,int h, int d, int b, int g) {
		super ( coordX,  coordY,  pvMax, e, h,  d,  b,  g) ;
	}
	
	public abstract void action();

	public void déplacement() {
		
	}
}
