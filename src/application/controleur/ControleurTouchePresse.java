package application.controleur;

import application.modele.Environnement;
import application.vue.CraftVue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ControleurTouchePresse implements EventHandler<KeyEvent>{

	private Environnement env;
	private CraftVue craft;


	public ControleurTouchePresse(Environnement env, CraftVue craft) {
		this.env = env;
		this.craft = craft;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
		case D:
			env.getJoueur().setClickD(true);
			break;
		case Q:
			env.getJoueur().setClickQ(true);
			break;
		case SPACE:
			env.getJoueur().saut();
			break;
			
		case DIGIT1:
		case AMPERSAND:
			env.getJoueur().getInventaire().changerCase(0);
			break;
			
		case DIGIT2:
			env.getJoueur().getInventaire().changerCase(1);
			break;
		case DIGIT3:
		case QUOTEDBL:
			env.getJoueur().getInventaire().changerCase(2);
			break;
			
		case DIGIT4:
		case QUOTE:
			env.getJoueur().getInventaire().changerCase(3);
			break;
			
		case DIGIT5:
		case LEFT_PARENTHESIS:
			env.getJoueur().getInventaire().changerCase(4);
			break;
			
		case E :
			craft.gestionOuverture();
			break;
			
		case UNDEFINED:
			switch (event.getText()) {
			case "é":
				env.getJoueur().getInventaire().changerCase(1);
				break;
			}
			break;
		default:
			break; 
		}
	}


}