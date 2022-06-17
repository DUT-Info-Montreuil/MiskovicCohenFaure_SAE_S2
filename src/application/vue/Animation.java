package application.vue;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;

public class Animation {
	
	private ImageView image;

	public Animation (DoubleProperty x,ImageView i) {
		this.image=i;

		
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
	
//	
//	public void animUnTour() {
//		for (Animation a : this.animations) {
//			a.action();
//		}
//	}
	
	public  void action () {
		
	}
}
