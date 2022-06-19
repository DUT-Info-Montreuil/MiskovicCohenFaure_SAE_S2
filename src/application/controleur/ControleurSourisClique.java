package application.controleur;

import java.util.Map;

import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Outils;
import application.modele.items.Bloc;
import application.modele.items.utilitaires.Pioche;
import application.vue.ImageMap;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
		if (event.getButton() == MouseButton.PRIMARY) {
			Joueur j = this.env.getJoueur();
			//Verification de la distance
			if (Outils.verifRange(j.getX(), j.getY(), this.numeroCase)) {

				//PIOCHE
				if (j.getInventaire().itemEnMain() instanceof Pioche) {
					if (env.getTerrain().getTable()[numeroCase] > 0) {
						Pioche p = (Pioche) j.getInventaire().itemEnMain();
						//Suppression de la case dans le modèle
						ImageView img= (ImageView) event.getSource();
						Image ciel = images.get("B0"); 

						if (img.getId() == "4") {
							this.env.getTerrain().supprimerCase(numeroCase);
							j.getCompteurMateriaux().get(1).ajouterMat(1);
							img.setImage(ciel);
						}
						else if (img.getId() == "5") {
							if (p.getMateriaux() >= 1) {
								j.getCompteurMateriaux().get(2).ajouterMat(1);
								this.env.getTerrain().supprimerCase(numeroCase);
								img.setImage(ciel);
							}
						}
						else if (img.getId() == "6") {
							if (p.getMateriaux() >= 2) {
								j.getCompteurMateriaux().get(3).ajouterMat(1);
								this.env.getTerrain().supprimerCase(numeroCase);
								img.setImage(ciel);
							}
						}
						else {
							j.getInventaire().ajouterItem(new Bloc(Integer.parseInt(img.getId())));
							this.env.getTerrain().supprimerCase(numeroCase);
							img.setImage(ciel);
						}
					}
				}

				//Verif que joueur pose pas bloc sur lui
				if (!(Outils.memeTile(j.getX(), j.getY(), this.numeroCase))) {

					//BLOC
					if  (j.getInventaire().itemEnMain() instanceof Bloc) {
						ImageView img= (ImageView) event.getSource();
						if (env.getTerrain().getTable()[numeroCase] == 0) {
							String idBloc = j.getInventaire().itemEnMain().getId();
							j.getInventaire().poserBloc(idBloc);
							img.setImage(images.get(idBloc));
							img.setId(String.valueOf(idBloc.charAt(1)));
							this.env.getTerrain().changerCase(numeroCase, (int) idBloc.charAt(1));
						}
					}
				}
			}
		}
	}
}



