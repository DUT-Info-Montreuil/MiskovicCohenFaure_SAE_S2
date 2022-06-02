package application.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class PVVue {
	private HBox pointsDeVie;
	private int pvMax;
	
	public PVVue (HBox pointsDeVie, int pvMax) {
		this.pointsDeVie = pointsDeVie;
		this.pvMax = pvMax;
	}
	
	public void initPV() {
		ImageView img = null;
		
		for (int i = 0; i < pvMax; i++) {
			img = new ImageView(new Image("application/ressource/coeurRempli.png"));
			pointsDeVie.getChildren().add(img);
		}
	}
	
	public void changerPV(int pv) {
		int i = 0;
		
		while (i < pvMax) {
			while (i < pv) {
				pointsDeVie.getChildren().set(i, new ImageView(new Image("application/ressource/coeurRempli.png")));
				i++;
			}
			pointsDeVie.getChildren().set(i, new ImageView(new Image("application/ressource/coeurVide.png")));
			i++;
		}
	}
}
