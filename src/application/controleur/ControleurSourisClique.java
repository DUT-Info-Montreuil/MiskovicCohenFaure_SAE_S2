package application.controleur;


import java.util.Map;

import application.modele.Environnement;
import application.modele.Outils;
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
		int caseJoueur = Outils.coordToTile(env.getJoueur().getX()+16, env.getJoueur().getY());

		//Verif range
		if ( (caseJoueur - this.numeroCase < 5 && caseJoueur - this.numeroCase > -5) 
			|| ( (caseJoueur-120) - this.numeroCase < 5 && (caseJoueur-120) - this.numeroCase > -5) 
			|| ( (caseJoueur+120) - this.numeroCase < 5 && (caseJoueur+120) - this.numeroCase > -5) ) {
			
			//PIOCHE
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
			
			//Verif que joueur pose pas bloc sur lui
			if (!(caseJoueur - this.numeroCase < 1 && caseJoueur - this.numeroCase > -1))
				
				//BLOC
				if  (env.getJoueur().getInventaire().itemEnMain() instanceof Bloc) {
					ImageView img= (ImageView) event.getSource();
					if (env.getTerrain().getTable()[numeroCase] == 0) {
						String idBloc = env.getJoueur().getInventaire().itemEnMain().getId();
						this.env.getJoueur().getInventaire().poserBloc(idBloc);
						img.setImage(images.get(idBloc));
						img.setId(String.valueOf(idBloc.charAt(1)));
						this.env.getTerrain().changerCase(numeroCase, (int) idBloc.charAt(1));
					}
				}
		}
		
		//EPEE
		if (env.getJoueur().getInventaire().itemEnMain() instanceof Epee) {
			this.env.getJoueur().attaque();
		}
	}
}



