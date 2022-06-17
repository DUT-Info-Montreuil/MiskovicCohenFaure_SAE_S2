package application.vue;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CraftVue {

	private Pane terrainPane;
	private Pane craftPane;
	private boolean inventaireOuvert;

	public CraftVue (Pane terrain, Pane craft) {
		this.terrainPane=terrain;
		this.craftPane=craft;
		this.craftPane.setVisible(false);
		this.inventaireOuvert=false;
	}

	public void gestionOuverture() {
		if (this.inventaireOuvert)
			this.fermerCraft();
		else
			this.ouvrirCraft();
	}

	public void ouvrirCraft() {
		this.craftPane.setVisible(true);
		this.craftPane.requestFocus();
		this.inventaireOuvert = true;
	}

	public void fermerCraft() {
		this.craftPane.setVisible(false);
		this.terrainPane.requestFocus();
		this.inventaireOuvert = false;
	}
	
	public void cacherBoutton(Button bout) {
		bout.setVisible(false);
	}
	
	public void ameliorerText(Text txt) {
		
	}
	
}
