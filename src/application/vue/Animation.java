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
	private DoubleProperty x;
	private DoubleProperty y;


	
	public ImageView getImage() {
		return image;
	}


	public Animation (DoubleProperty x,DoubleProperty y,ImageView i) {
		this.image=i;
		this.x=x;
		this.y=y;
		
		x.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				retournement((double)(oldValue),(double)(newValue));
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
	
	public  void action () {

	}
	
	public DoubleProperty getX() {
		return x;
	}


	public DoubleProperty getY() {
		return y;
	}
	
}
