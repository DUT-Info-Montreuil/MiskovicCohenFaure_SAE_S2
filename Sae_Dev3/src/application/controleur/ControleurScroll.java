package application.controleur;

import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

public class ControleurScroll implements EventHandler<ScrollEvent>{

	private Environnement env;
	
	public ControleurScroll(Environnement env) {
		this.env = env;
	}
	
	@Override
	public void handle(ScrollEvent event) {
		if (event.getDeltaY() > 0) {
			env.getJoueur().getInventaire().listItemsIndex().augmenterIndex();
		}
		else if (event.getDeltaY() < 0) {
			env.getJoueur().getInventaire().listItemsIndex().baisserIndex();
		}
		
	}

}
