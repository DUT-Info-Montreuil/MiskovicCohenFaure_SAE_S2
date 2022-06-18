package application.vue;

import java.util.Map;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArbreVue {
	Map<String, Image> images;
	
	public ArbreVue() {
		images = ImageMap.images;
	}
	
	public ImageView creerArbre(String id) {
		Random r = new Random();
		ImageView arbre = new ImageView(images.get("ARB" + (r.nextInt(5 - 1) + 1)));
		arbre.setId(id);
		return arbre;
	}
	
	
}
