package application.vue;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Animation {
	
	private DoubleProperty x;
	private ImageView image;
	private ArrayList <Animation> animations;

	public Animation (DoubleProperty x,ImageView i) {
		this.x=x;
		this.image=i;
		this.animations=new ArrayList<Animation>();

		
		x.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				retournement((double)(oldValue),(double)(newValue));
			}	
		});		
	}
	
	
	public void retournement (double ancien, double nouveau) {
		if (nouveau>ancien) {
			this.image.setScaleX(1);
		}
		else {
			this.image.setScaleX(-1);
		}
	}
	
	public void ajouterAnim (Animation a) {
		this.animations.add(a);
	}
	
	public void animUnTour() {
		for (Animation a : this.animations) {
			a.action();
		}
	}
	
	public abstract void action ();
}
