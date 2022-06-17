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
	private ImageView image;
	private ArrayList <Image> images;
	private int attTemps;

	private int temps;
	

	public JoueurVue (DoubleProperty d, DoubleProperty g, DoubleProperty x,ImageView i) {
		super (x,i);
		this.d=d;
		this.g=g;
		this.image=i;
		this.images=new ArrayList<Image>();
		this.images.add(new Image("application/ressource/20.png"));
		this.images.add(new Image("application/ressource/21.png"));
		this.images.add(new Image("application/ressource/22.png"));
		this.images.add(new Image("application/ressource/23.png"));
		this.images.add(new Image("application/ressource/24.png"));
		this.attTemps=0;
		this.temps=0;
		
		x.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mouvementHoriz((double)(oldValue),(double)(newValue));
			}	
		});
		
		
		
	}
	public void setAttTemps(int attTemps) {
		this.attTemps = attTemps;
	}
	
	public void mouvementHoriz (double ancien, double nouveau) {
		this.image.setImage(this.images.get(temps/10));
		if (d.get()<=0.25&&g.get()<=0.25) {
			this.image.setImage(this.images.get(0));
		}
		this.temps++;
		if (temps==30) {
			this.temps=0;
		}
	}
	
	public void attaqueAnimation () {
		if (this.attTemps>6) {
			this.image.setImage(this.images.get(3));
			attTemps--;
		}
		else if (this.attTemps>3) {
			this.image.setImage(this.images.get(4));
			attTemps--;
		}
		else if (this.attTemps>1) {
			this.image.setImage(this.images.get(3));
			attTemps--;
		}
		else if (attTemps==1){
			this.image.setImage(this.images.get(0));
			attTemps--;
		}
	}
	
	public void retournement (double ancien, double nouveau) {
		if (nouveau>ancien) {
			this.image.setScaleX(1);
		}
		else {
			this.image.setScaleX(-1);
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
