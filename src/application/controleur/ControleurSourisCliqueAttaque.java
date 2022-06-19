package application.controleur;

import application.modele.Joueur;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;
import application.vue.JoueurVue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurSourisCliqueAttaque implements EventHandler<MouseEvent>{

	private JoueurVue joueurV;
	private Joueur j;

	public ControleurSourisCliqueAttaque(Joueur j, JoueurVue joueurV) {
		this.joueurV=joueurV;
		this.j=j;
	}
	@Override
	public void handle(MouseEvent event) {
		if (j.getInventaire().itemEnMain() instanceof Epee) {
			j.attaque();
			joueurV.setAttTemps(6);
		}
		//ARC
		else if (j.getInventaire().itemEnMain() instanceof Arc) {
			j.fleche(); 
		}
	}
}