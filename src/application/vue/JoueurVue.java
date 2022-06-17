package application.vue;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JoueurVue extends Animation {
	
	private DoubleProperty d;
	private DoubleProperty g;
	private DoubleProperty x;
	private ImageView image;
	private ArrayList <Image> images;
	private int attTemps;
	private int temps;
	

	public JoueurVue (DoubleProperty d, DoubleProperty g, DoubleProperty x,ImageView i) {
		super (x,i);
		this.d=d;
		this.g=g;
		this.x=x;
		this.image=i;
		this.images=new ArrayList<Image>();
		this.images.add(new Image("application/ressource/20.png"));
		this.images.add(new Image("application/ressource/21.png"));
		this.images.add(new Image("application/ressource/22.png"));
		this.attTemps=0;
		this.temps=0;
		
		x.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mouvementHoriz((double)(oldValue),(double)(newValue));
			}	
		});
		
		
		
	}
	
	private void mouvementHoriz (double ancien, double nouveau) {
		this.image.setImage(this.images.get(temps/10));
		if (d.get()<=0.25&&g.get()<=0.25) {
			this.image.setImage(this.images.get(0));
		}
		this.temps++;
		if (temps==30) {
			this.temps=0;
		}
	}
	
	private void attaqueAnimation () {
		if (this.attTemps<7) {
			this.image.setImage(null);
			temps++;
		}
		else if (temps==7) {
			this.image.setImage(this.images.get(0));		
			this.temps=0;
		}
	}

	@Override
	public void action() {
		this.attaqueAnimation();		
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
