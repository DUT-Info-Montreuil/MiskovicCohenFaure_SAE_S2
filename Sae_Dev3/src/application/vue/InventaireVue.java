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

    Map<String, Image> images;
    
    public InventaireVue(HBox inventaireAff, HBox inventaireSelect, HBox inventaireItems, int tailleInventaire) {
    	this.inventaireAff = inventaireAff;
    	this.inventaireSelect = inventaireSelect;
    	this.tailleInventaire = tailleInventaire;
    	this.inventaireItems = inventaireItems;
    	
    	caseInventaireSelect = new Image("application/ressource/inventaire/caseInventaireSelect.png");
    	images = new HashMap<>();
    }
    
    public void initImagesPioche() {
    	for (int i = 0; i < 2; i++) {
    		images.put("P" + i, new Image("application/ressource/inventaire/pioche/P" + i + ".png"));
    	}
    }
    
    public void initImagesEpee() {
    	for (int i = 0; i < 2; i++) {
    		images.put("E" + i, new Image("application/ressource/inventaire/epee/E" + i + ".png"));
    	}
    }
    
    public void initImagesArc() {
    	for (int i = 0; i < 2; i++) {
    		images.put("A" + i, new Image("application/ressource/inventaire/arc/A" + i + ".png"));
    	}
    }
    
    public void initImagesHache() {
    	for (int i = 0; i < 2; i++) {
    		images.put("H" + i, new Image("application/ressource/inventaire/hache/H" + i + ".png"));
    	}
    }
    
    public void initImagesBloc() {
    	for (int i = 0; i < 1; i++) {
    		images.put("B" + i, new Image("application/ressource/inventaire/bloc/B" + i + ".png"));
    	}
    }
    
    public void initInventaire() {
    	this.initImagesPioche();
    	this.initImagesEpee();
    	this.initImagesArc();
    	this.initImagesHache();
    	this.initImagesBloc();
    	
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
    	System.out.println("curseur : " + curseur);
    	if (idItem == null) {
    		inventaireItems.getChildren().get(curseur).setVisible(false);
    	}
    	else {
    		inventaireItems.getChildren().get(curseur).setVisible(true);
    		((ImageView) inventaireItems.getChildren().get(curseur)).setImage(images.get(idItem));
    	}
    }
    
    

}
