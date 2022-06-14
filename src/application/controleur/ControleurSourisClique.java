package application.controleur;


import java.util.Map;

import application.modele.Environnement;
import application.modele.Fleche;
import application.modele.Joueur;
import application.modele.Outils;
import application.modele.items.Bloc;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;
import application.modele.items.utilitaires.Pioche;
import application.vue.ImageMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ControleurSourisClique implements EventHandler<MouseEvent>{

	private int numeroCase;
	private Environnement env;
	Map<String,Image> images;
	private Controleur controleur;

	public ControleurSourisClique(int numCase, Environnement env, Controleur contro) {
		this.numeroCase = numCase;
		this.env=env;
		images = ImageMap.images;
		this.controleur=contro;

	}

	@Override
	public void handle(MouseEvent event) {

		//Verif range
		if (Outils.verifRange(env.getJoueur().getX(), env.getJoueur().getY(), this.numeroCase)) {

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
			if (!(Outils.verifMemeTile(env.getJoueur().getX(), env.getJoueur().getY(), this.numeroCase))) {

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
		}

		//EPEE
		if (env.getJoueur().getInventaire().itemEnMain() instanceof Epee) {
			this.env.getJoueur().attaque();
		}
		else if (env.getJoueur().getInventaire().itemEnMain() instanceof Arc) {
			Joueur j=this.env.getJoueur();
			new Fleche(j.getX(), j.getY(), this.env, j.isVersDroite());
		}
	} 
}



