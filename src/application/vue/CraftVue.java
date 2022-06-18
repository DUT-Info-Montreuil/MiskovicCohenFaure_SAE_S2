package application.vue;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

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
	
	public void cacherTextArea(TextArea txt) {
		txt.setText("Plus d'amélioration\ndisponible\nBravo!");
		this.terrainPane.requestFocus();
	}
	
	public void ameliorerTextEpee(TextArea txt, int niv) {
		if (niv==1)
			txt.setText("Recette :\n"
					+ "\n"
					+ "-1 Bois\n"
					+ "-2 Or");
		else
			txt.setText("Recette :\n"
					+ "\n"
					+ "-1 Bois\n"
					+ "-2 Diamant");
		this.terrainPane.requestFocus();
	}
	
	public void ameliorerTextPiocheHache(TextArea txt, int niv) {
		if (niv==1)
			txt.setText("Recette :\n"
					+ "\n"
					+ "-1 Bois\n"
					+ "-3 Or");
		else
			txt.setText("Recette :\n"
					+ "\n"
					+ "-1 Bois\n"
					+ "-3 Diamant");
		this.terrainPane.requestFocus();
	}
	
}
