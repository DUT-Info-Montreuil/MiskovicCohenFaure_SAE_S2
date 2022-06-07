package application.controleur;


import application.modele.Environnement;
import application.modele.utilitaires.Pioche;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ControleurSourisClique implements EventHandler<MouseEvent>{

	private int numeroCase;
	private Environnement env;

	public ControleurSourisClique(int numCase, Environnement env) {
		this.numeroCase = numCase;
		this.env=env;

	}

	@Override
	public void handle(MouseEvent event) {
		//Suppression de la case dans le modèle
		if (env.getJoueur().getInventaire().itemEnMain() instanceof Pioche) {
			this.env.getTerrain().supprimerCase(numeroCase);
			//Affichage de l'id 0
			ImageView img= (ImageView) event.getSource(); //Permet de récupérer l'ImgView
			Image ciel = new Image("application/ressource/0.png");
			img.setImage(ciel);
		}
	}
}



