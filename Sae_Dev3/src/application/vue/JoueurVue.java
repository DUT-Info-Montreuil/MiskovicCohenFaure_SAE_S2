package application.vue;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JoueurVue {
	
	private IntegerProperty coordX;
	private IntegerProperty coordY;
	private ImageView image;
	private ArrayList <Image> images;
	private int temps;
	
//	this.ajouterImage(new Image("application/ressource/20.png")); 
//	this.ajouterImage(new Image("application/ressource/21.png"));
//	this.ajouterImage(new Image("application/ressource/22.png"));
	
	public JoueurVue (IntegerProperty x, IntegerProperty y,ImageView i) {
		this.coordX=x;
		this.coordY=y;
		this.image=i;
		this.images=new ArrayList<Image>();
		this.images.add(new Image("application/ressource/20.png"));
		this.images.add(new Image("application/ressource/21.png"));
		this.images.add(new Image("application/ressource/22.png"));
		this.temps=0;
		
		coordX.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mouvementHoriz((int)(oldValue),(int)(newValue));
			}	
		});
	}
	
	private void mouvementHoriz (int ancien, int nouveau) {
		this.image.setImage(this.images.get(temps/10));
		if (ancien<nouveau) {
			this.image.setScaleX(1);
		}
		else {
			this.image.setScaleX(-1);
		}
		this.temps++;
		if (temps==30) {
			this.temps=0;
		}
	}


//	public void mouvement(int gauche, int droite , int y , ImageView i) {
//		if (droite==gauche||this.getTemps()==29) {
//			this.setTemps(0);
//		}
//		else {
//			this.setTemps(this.getTemps()+1);
//		}
//		i.setImage(this.getSprites().get(this.getTemps()/10));
//	}
	
}
