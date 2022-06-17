package application.controleur;


import java.util.Map;

import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Outils;
import application.modele.items.Bloc;
import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;
import application.modele.items.utilitaires.Pioche;
import application.vue.ImageMap;
import application.vue.JoueurVue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ControleurSourisClique implements EventHandler<MouseEvent>{

	private int numeroCase;
	private Environnement env;
	Map<String,Image> images;
	private JoueurVue jv;

	public ControleurSourisClique(int numCase, Environnement env, JoueurVue v) {
		this.numeroCase = numCase;
		this.env=env;
		images = ImageMap.images;
		this.jv=v;
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
					ImageView img= (ImageView) event.getSource();
					if (img.getId() == "4")
						this.env.getJoueur().getCompteurMateriaux().get(1).ajouterMat(1);
					else if (img.getId() == "5")
						this.env.getJoueur().getCompteurMateriaux().get(2).ajouterMat(1);
					else if (img.getId() == "6")
						this.env.getJoueur().getCompteurMateriaux().get(3).ajouterMat(1);
					else
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
			this.env.getJoueur().fleche();
		}
	} 
}



