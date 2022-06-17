package application.vue;


import application.controleur.Controleur;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Animation {
	
	private ImageView image;
	private int temps;
	private Label l;
	private DoubleProperty x;
	private DoubleProperty y;
	private Controleur control;

	
	public ImageView getImage() {
		return image;
	}


	public Animation (DoubleProperty x,DoubleProperty y,IntegerProperty pv,ImageView i,Controleur control) {
		this.image=i;
		this.temps=0;
		this.x=x;
		this.y=y;
		this.control=control;
		
		x.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				retournement((double)(oldValue),(double)(newValue));
			}	
		});		
		
		pv.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				perdPV((int)(oldValue),(int)(newValue));
			}	
		});		
	}
	
	
	public void retournement (double ancien, double nouveau) {
		if (nouveau-ancien<-0.7) {
			this.image.setScaleX(-1);
		}
		else if (nouveau-ancien>0.7){
			this.image.setScaleX(1);
		}
	}
	
//	
//	public void animUnTour() {
//		for (Animation a : this.animations) {
//			a.action();
//		}
//	}
	
	public  void action () {
		afficherPerte();
	}
	
	public void perdPV (int ancien, int nouveau) {
		if (nouveau>0) {
			this.temps=15;
			setPerte(nouveau-ancien);
		}
	}
	
	public void setPerte (int perte) {
		if (this.temps==15) {
			this.l=new Label();
			this.control.ajouterLabel(l, this.x.get(), this.y.get()-32, String.valueOf(perte));
		}

	}
	
	public void afficherPerte () {
		if(this.temps==0) {
			this.control.retirerLabel(l);
		}
		else {
			temps--;
		}
	}
}
