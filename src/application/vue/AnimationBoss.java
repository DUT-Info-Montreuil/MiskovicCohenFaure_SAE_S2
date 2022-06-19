package application.vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AnimationBoss extends AnimationMob {

	public AnimationBoss(DoubleProperty x, DoubleProperty y, IntegerProperty pv, ImageView i, Pane terrain) {
		super(x, y, pv, i, terrain);
		pv.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((int)newValue<=0) {
					confettis();
				}
			}	
		});	
	}
	
	public void confettis () {
		ImageView im=new ImageView();
		this.getTerrain().getChildren().add(im);
		im.setImage(new Image("application/ressource/confettis.gif"));
		im.setTranslateX(6465);
		im.setTranslateY(500);
		im.setScaleX(1.2);
		im.setScaleY(2.5);
	}
}
