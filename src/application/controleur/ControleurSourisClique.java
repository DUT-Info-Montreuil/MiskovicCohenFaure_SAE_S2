package application.controleur;


import java.util.Map;

import application.modele.Environnement;
import application.modele.items.Bloc;
import application.modele.items.utilitaires.Epee;
import application.modele.items.utilitaires.Pioche;
import application.vue.ImageMap;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ControleurSourisClique implements EventHandler<MouseEvent>{

	private int numeroCase;
	private Environnement env;
	Map<String,Image> images;

	public ControleurSourisClique(int numCase, Environnement env) {
		this.numeroCase = numCase;
		this.env=env;
		images = ImageMap.images;

	}

	@Override
	public void handle(MouseEvent event) {
		if (env.getJoueur().getInventaire().itemEnMain() instanceof Pioche) {
			if (env.getTerrain().getTable()[numeroCase] > 0) {
				//Suppression de la case dans le modèle
				this.env.getTerrain().supprimerCase(numeroCase);
				//Affichage de l'id 0
				ImageView img= (ImageView) event.getSource(); //Permet de récupérer l'ImgView
				env.getJoueur().getInventaire().ajouterItem(new Bloc(Integer.parseInt(img.getId())));
				Image ciel = images.get("B0");
				img.setImage(ciel);
			}
		}
		
		if (env.getJoueur().getInventaire().itemEnMain() instanceof Bloc) {
			ImageView img= (ImageView) event.getSource();
			if (env.getTerrain().getTable()[numeroCase] == 0) {
				String idBloc = env.getJoueur().getInventaire().itemEnMain().getId();
				this.env.getJoueur().getInventaire().poserBloc(idBloc);
				img.setImage(images.get(idBloc));
				this.env.getTerrain().changerCase(numeroCase, (int) idBloc.charAt(1));
			}
		}
		else if (env.getJoueur().getInventaire().itemEnMain() instanceof Epee) {
			this.env.getJoueur().attaque();
		}
	} 
}



