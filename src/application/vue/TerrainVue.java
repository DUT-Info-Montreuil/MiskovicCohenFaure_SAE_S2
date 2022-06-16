package application.vue;

import java.util.Map;

import application.controleur.ControleurSourisClique;
import application.controleur.ControleurSourisSurvolage;
import application.controleur.ControleurSourisSortie;
import application.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class TerrainVue {

	private Environnement env;
	private TilePane terrainMap;
	private Map<String, Image> images;



	public TerrainVue(Environnement env, TilePane terrainMap) {
		super();
		this.env = env;
		this.terrainMap = terrainMap;
		images = ImageMap.images;
	}

	public void initTerrain() {
		ImageView img = null;
		int[] terrain = env.getTerrain().getTable();
		this.initImagesBloc();

		for (int i = 0; i < terrain.length; i++) {
			switch (terrain[i]) {
			case 0:
				img = new ImageView(images.get("B0"));
				img.setId("0");
				break;
			case 1: 
				img = new ImageView(images.get("B1"));
				img.setId("1");
				break;
			case 2: 
				img = new ImageView(images.get("B2"));
				img.setId("2");
				break;
			}
			terrainMap.getChildren().add(img);
			img.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new ControleurSourisSurvolage(i, env));
			img.addEventHandler(MouseEvent.MOUSE_EXITED, new ControleurSourisSortie());
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurSourisClique(i,env));
		}
	}
	
	public void initImagesBloc() {
    	for (int i = 0; i < 3; i++) {
    		images.put("B" + i, new Image("application/ressource/" + i + ".png"));
    	}
    }

}
