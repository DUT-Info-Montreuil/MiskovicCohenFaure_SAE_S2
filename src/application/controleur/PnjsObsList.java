package application.controleur;

import application.modele.pnjs.Pnj;
import javafx.collections.ListChangeListener;

public class PnjsObsList implements ListChangeListener<Pnj >{
	private Controleur controleur;

	public PnjsObsList (Controleur c) {
		controleur = c;
	}

	@Override
	public void onChanged(Change<? extends Pnj> c) {
		c.next();
		for (Pnj m: c.getAddedSubList()) {
			controleur.creerSpritePnj(m);
		}
	}
}