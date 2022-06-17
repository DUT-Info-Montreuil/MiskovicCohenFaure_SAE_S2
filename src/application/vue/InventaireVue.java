package application.vue;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class InventaireVue {
	
    private HBox inventaireAff;
    private HBox inventaireSelect;
    private HBox inventaireItems;
    private int tailleInventaire;
    private Image caseInventaireSelect;
    private Map<String, Image> images;
    
    public InventaireVue(HBox inventaireAff, HBox inventaireSelect, HBox inventaireItems, int tailleInventaire) {
    	this.inventaireAff = inventaireAff;
    	this.inventaireSelect = inventaireSelect;
    	this.tailleInventaire = tailleInventaire;
    	this.inventaireItems = inventaireItems;
    	images = ImageMap.images;
    	
    	caseInventaireSelect = new Image("application/ressource/inventaire/caseInventaireSelect.png");
    }
    
    public void initInventaire() {
    	
    	inventaireAff.getChildren().add(new ImageView(images.get("E0")));
    	inventaireAff.getChildren().add(new ImageView(images.get("P0")));
    	inventaireAff.getChildren().add(new ImageView(images.get("H0")));
    	inventaireAff.getChildren().add(new ImageView(images.get("A0")));
    	inventaireAff.getChildren().add(new ImageView(images.get("B0")));
    	
    	this.initInventaireSelect();
    	this.initInventaireItem();
	}
    
    public void initInventaireSelect() {
    	for (int i = 0; i < tailleInventaire; i++) {
    		inventaireSelect.getChildren().add(new ImageView(caseInventaireSelect));
    		this.enleverCurseur(i);
    	}
    	this.positionnerCurseur(0);
    }
    
    public void initInventaireItem() {
    	for (int i = 0; i < tailleInventaire; i++) {
    		inventaireItems.getChildren().add(new ImageView(caseInventaireSelect));
    		inventaireItems.getChildren().get(i).setVisible(false);
    	}
    }
    
    public void positionnerCurseur(int curseur) {
    	inventaireSelect.getChildren().get(curseur).setVisible(true);
    }
    
    public void enleverCurseur(int curseur) {
    	inventaireSelect.getChildren().get(curseur).setVisible(false);
    }
    
    public void changerImage(int curseur, String idItem) {
    	if (idItem == null) {
    		inventaireItems.getChildren().get(curseur).setVisible(false);
    	}
    	else {
    		inventaireItems.getChildren().get(curseur).setVisible(true);
    		System.out.println(idItem);
    		((ImageView) inventaireItems.getChildren().get(curseur)).setImage(images.get(idItem));
    	} 

    } 
}
