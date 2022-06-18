package application.controleur;


import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Outils;
import application.modele.items.Item;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;
import application.modele.items.utilitaires.Hache;
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
		
		Joueur j = this.env.getJoueur();
		Item itemEnMain = j.getInventaire().itemEnMain();
		
		if (!(itemEnMain instanceof Epee) && !(itemEnMain instanceof Arc) && !(itemEnMain instanceof Hache) && !(itemEnMain==null)) {
			if (Outils.verifRange(j.getX(), j.getY(), this.numeroCase)){
				ImageView img=(ImageView) event.getSource();
				img.setOpacity(0.8);
			}
		}
	}
}



