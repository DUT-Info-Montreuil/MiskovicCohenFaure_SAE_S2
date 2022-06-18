package application.controleur;

import application.modele.Arbre;
import javafx.collections.ListChangeListener;

public class ArbreObsList implements ListChangeListener<Arbre >{
	private Controleur controleur;

	public ArbreObsList (Controleur c) {
		controleur = c;
	}

	@Override
	public void onChanged(Change<? extends Arbre> c) {
		c.next();
		for (Arbre a: c.getAddedSubList()) {
			controleur.creerSpriteArbre(a);
		}
		for (Arbre a: c.getRemoved()) {
			controleur.enleverSprite(a.getId());
		}
	}
}
