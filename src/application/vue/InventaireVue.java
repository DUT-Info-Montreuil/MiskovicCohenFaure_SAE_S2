package application.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InventaireVue {
	
    private HBox inventaireAff;
    private HBox inventaireSelect;
    private int tailleInventaire;
    
    public InventaireVue(HBox inventaireAff, HBox inventaireSelect, int tailleInventaire) {
    	this.inventaireAff = inventaireAff;
    	this.inventaireSelect = inventaireSelect;
    	this.tailleInventaire = tailleInventaire;
    }
    
    public void initInventaire() {
		ImageView img = null;
		Rectangle r;
		
		for (int i = 0; i < tailleInventaire; i++) {
			img = new ImageView(new Image("application/ressource/Inventaire" + i + ".png"));
			inventaireAff.getChildren().add(img);
			
			r = new Rectangle(32, 32);
			r.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: white");
			r.setFill(Color.TRANSPARENT);
			inventaireSelect.getChildren().add(r);
		}
	}
    
    public void positionnerCurseur(int curseur) {
    	inventaireSelect.getChildren().set(curseur, new ImageView(new Image("application/ressource/caseInventaireSelect.png")));
    }
    
    public void enleverCurseur(int curseur) {
    	Rectangle r = new Rectangle(32, 32);
		r.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: white");
		r.setFill(Color.TRANSPARENT);
    	inventaireSelect.getChildren().set(curseur, r);
    }
    
    public void changerImage(int curseur, String idItem) {
    	inventaireAff.getChildren().set(curseur, new ImageView(new Image("application/ressource/" + idItem + ".png")));
    }
    
    

}
