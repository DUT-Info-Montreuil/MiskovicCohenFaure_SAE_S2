package application.controleur;

import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ControleurToucheRelache implements EventHandler<KeyEvent>{

private Environnement env;

	
	public ControleurToucheRelache(Environnement env) {
		this.env = env;
	}
	
	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
			case D:
				env.getJoueur().setDirDroite(0);
				break;
			case Q:
				env.getJoueur().setDirGauche(0);
				break;
			default:
				break;
		}
	}
}
