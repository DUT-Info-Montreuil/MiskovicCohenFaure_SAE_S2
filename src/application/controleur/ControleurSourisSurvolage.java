package application.controleur;


import application.modele.Environnement;
import application.modele.Outils;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleurSourisSurvolage implements EventHandler<MouseEvent>{

	private int numeroCase;
	Environnement env;
	
	public ControleurSourisSurvolage(int numCase, Environnement env) {
		this.env=env;
		this.numeroCase = numCase;
	}
	

	@Override
	public void handle(MouseEvent event) {
		if (!(env.getJoueur().getInventaire().itemEnMain() instanceof Epee) && !(env.getJoueur().getInventaire().itemEnMain() instanceof Arc) && !(env.getJoueur().getInventaire().itemEnMain()==null)) {
			if (Outils.verifRange(env.getJoueur().getX(), env.getJoueur().getY(), this.numeroCase)){
				ImageView img=(ImageView) event.getSource();
				img.setOpacity(0.8);
			}
		}
	}
}



