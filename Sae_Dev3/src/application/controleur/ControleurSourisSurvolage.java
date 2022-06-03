package application.controleur;


import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleurSourisSurvolage implements EventHandler<MouseEvent>{

	public ControleurSourisSurvolage() {
	}

	@Override
	public void handle(MouseEvent event) {
		ImageView img=(ImageView) event.getSource();
		img.setOpacity(0.8);
	}
}



