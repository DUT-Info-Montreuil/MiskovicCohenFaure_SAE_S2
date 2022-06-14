package application.vue;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MobVue {
	
	Map<String, Image> images;
	
	public MobVue() {
		images = ImageMap.images;
	}
	
	public ImageView creerSlime(String id) {
		ImageView slime = new ImageView(images.get("slime"));
		slime.setId(id);
		return slime;
	}
	
	public ImageView creerFleche(String id) {
		ImageView fleche = new ImageView(images.get("fleche"));
		fleche.setId(id);
		return fleche;
	}
}
