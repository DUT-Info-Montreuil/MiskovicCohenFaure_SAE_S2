package application.vue;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class PVVue {
	private HBox pointsDeVie;
	private int pvMax;
	private ArrayList<Image> images;
	
	public PVVue (HBox pointsDeVie, int pvMax) {
		this.pointsDeVie = pointsDeVie;
		this.pvMax = pvMax;
		
		images = new ArrayList<>();
		images.add(new Image("application/ressource/coeurRempli.png"));
		images.add(new Image("application/ressource/coeurVide.png"));
	}
	
	public void initPV() {
		 
		for (int i = 0; i < pvMax; i++) {
			pointsDeVie.getChildren().add(new ImageView(images.get(0)));
		}
	}
	
	public void changerPV(int pv) {
		int i = 0;
		
		while (i < pvMax) {
			while (i < pv) {
				((ImageView) pointsDeVie.getChildren().get(i)).setImage(images.get(0));
				i++;
			}
			((ImageView) pointsDeVie.getChildren().get(i)).setImage(images.get(1));
			i++;
		}
	}
}
