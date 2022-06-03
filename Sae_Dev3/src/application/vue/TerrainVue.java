package application.vue;

import application.controleur.ControleurSourisClique;
import application.controleur.ControleurSourisSurvolage;
import application.controleur.ControleurSourisSortie;
import application.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class TerrainVue {

	private Environnement env;
	private TilePane terrainMap;


	public TerrainVue(Environnement env, TilePane terrainMap) {
		super();
		this.env = env;
		this.terrainMap = terrainMap;


	}

	public void initTerrain() {
		ImageView img = null;
		Image imgCiel = new Image("application/ressource/0.png");
		Image imgHerbe = new Image("application/ressource/1.png");
		Image imgTerre = new Image("application/ressource/2.png"); 
		int[] terrain = env.getTerrain().getTable();

		for (int i = 0; i < terrain.length; i++) {
			switch (terrain[i]) {
			case 0: 
				img = new ImageView(imgCiel);
				break;
			case 1: 
				img = new ImageView(imgHerbe);
				break;
			case 2: 
				img = new ImageView(imgTerre);
>>>>>>> refs/heads/dev
				break;
			}
			terrainMap.getChildren().add(img);
			img.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new ControleurSourisSurvolage());
			img.addEventHandler(MouseEvent.MOUSE_EXITED, new ControleurSourisSortie());
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurSourisClique(i,env));
		}
	}

}
