package application.controleur;


import application.modele.Environnement;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleurSourisSurvolage implements EventHandler<MouseEvent>{

	Environnement env;
	
	public ControleurSourisSurvolage(Environnement env) {
		this.env=env;
	}
	

	@Override
	public void handle(MouseEvent event) {
		if (!(env.getJoueur().getInventaire().itemEnMain() instanceof Epee) && !(env.getJoueur().getInventaire().itemEnMain() instanceof Arc) && !(env.getJoueur().getInventaire().itemEnMain()==null)) {
			ImageView img=(ImageView) event.getSource();
			img.setOpacity(0.8);
		}
	}
}



