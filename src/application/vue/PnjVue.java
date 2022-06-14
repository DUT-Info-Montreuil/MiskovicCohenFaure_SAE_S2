package application.vue;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PnjVue {
	Map<String, Image> images;
	
	public PnjVue() {
		images = ImageMap.images;
	}
	
	public ImageView creerDocteur(String id) {
		ImageView docteur = new ImageView(images.get("docteur"));
		docteur.setId(id);
		return docteur;
	}
	
	
}
