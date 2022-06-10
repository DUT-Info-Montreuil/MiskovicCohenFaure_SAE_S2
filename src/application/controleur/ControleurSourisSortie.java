package application.controleur;


import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleurSourisSortie implements EventHandler<MouseEvent>{

	
	public ControleurSourisSortie() {
	}
		
	@Override
	public void handle(MouseEvent event) {
		
		ImageView img=(ImageView) event.getSource();
		img.setOpacity(1);
		

	}
}



