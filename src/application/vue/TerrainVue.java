package application.vue;

import java.util.Map;

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
			case 3: 
				img = new ImageView(images.get("B3"));
				img.setId("3");
				break;
			case 4: 
				img = new ImageView(images.get("B4"));
				img.setId("4");
				break;
			case 5: 
				img = new ImageView(images.get("B5"));
				img.setId("5");
				break;
			case 6: 
				img = new ImageView(images.get("B6"));
				img.setId("6");
				break;
			case 7: 
				img = new ImageView(images.get("B7"));
				img.setId("7");
				break;
			}
			terrainMap.getChildren().add(img);
			if (i < terrain.length-240 && img.getId()!="7") {
				img.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new ControleurSourisSurvolage(i, env));
				img.addEventHandler(MouseEvent.MOUSE_EXITED, new ControleurSourisSortie());
			} 
		}
	}

	public void initImagesBloc() {
		for (int i = 0; i < 8; i++) {
			images.put("B" + i, new Image("application/ressource/tiles/" + i + ".png"));
		}
	}

}
