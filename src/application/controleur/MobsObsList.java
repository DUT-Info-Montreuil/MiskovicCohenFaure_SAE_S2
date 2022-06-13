package application.controleur;

import application.modele.Mob;
import javafx.collections.ListChangeListener;

public class MobsObsList implements ListChangeListener<Mob >{
	private Controleur controleur;

	public MobsObsList (Controleur c) {
		controleur = c;
	}

	@Override
	public void onChanged(Change<? extends Mob> c) {
		c.next();
		for (Mob m: c.getAddedSubList()) {
			controleur.creerSpriteMob(m);
		}
		for (Mob m: c.getRemoved()) {
			controleur.enleverSprite(m.getId());
		}
	}
}