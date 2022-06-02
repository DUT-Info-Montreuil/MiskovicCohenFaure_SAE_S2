package application.controleur;

import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ControleurTouchePresse implements EventHandler<KeyEvent>{
	
	private Environnement env;

	
	public ControleurTouchePresse(Environnement env) {
		this.env = env;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
			case D:
				env.getJoueur().setDirDroite(2);
				break;
			case Q:
				env.getJoueur().setDirGauche(2);
				break;
			case SPACE:
				env.getJoueur().saut();
				break;
			case AMPERSAND:
				env.getJoueur().getInventaire().setIndexProperty(0);
				break;
			case QUOTEDBL:
				env.getJoueur().getInventaire().setIndexProperty(2);
				break;
			case QUOTE:
				env.getJoueur().getInventaire().setIndexProperty(3);
				break;
			case LEFT_PARENTHESIS:
				env.getJoueur().getInventaire().setIndexProperty(4);
				break;
			case MINUS:
				env.getJoueur().perdrePV(1);
				break;
			case UNDEFINED:
				switch (event.getText()) {
					case "é":
						env.getJoueur().getInventaire().setIndexProperty(1);
						break;
				}
				break;
			default:
				break;
		}
	}
	

}