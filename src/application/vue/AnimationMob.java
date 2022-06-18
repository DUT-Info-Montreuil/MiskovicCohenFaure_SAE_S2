package application.vue;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AnimationMob extends Animation {
	
	private int temps;
	private Label l;
	private Pane terrain;

	


	public Pane getTerrain() {
		return terrain;
	}



	public AnimationMob (DoubleProperty x,DoubleProperty y,IntegerProperty pv,ImageView i,Pane terrain) {
		super (x,y,i);
		this.temps=0;
		this.terrain=terrain;
		
		pv.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				perdPV((int)(oldValue),(int)(newValue));
			}	
		});		
	}
	
	
	
	public  void action () {
		afficherPerte();
	}
	
	public void perdPV (int ancien, int nouveau) {
		if (temps>0) {
			this.terrain.getChildren().remove(l);
		}
		this.temps=15;
		setPerte(nouveau-ancien);
	}
	
	public void setPerte (int perte) {
		if (this.temps==15) {
			this.l=new Label();
			if (perte<0) {
				this.l.setTextFill(javafx.scene.paint.Color.RED);
			}
			else {
				this.l.setTextFill(javafx.scene.paint.Color.GREEN);
			}
			this.terrain.getChildren().add(l);
			l.setTranslateX(this.getX().get());
			l.setTranslateY(this.getY().get()-32);
			l.setText(String.valueOf(perte));
		}
	}
	
	public void afficherPerte () {
		if(this.temps==0) {
			this.terrain.getChildren().remove(l);
			}
		else {
			temps--;
		}
	}
}
