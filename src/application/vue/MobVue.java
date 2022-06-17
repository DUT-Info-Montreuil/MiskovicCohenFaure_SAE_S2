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
	
	public ImageView creerArcher(String id) {
		ImageView fleche = new ImageView(images.get("archer"));
		fleche.setId(id);
		return fleche;
	}
	
	public ImageView creerSquelette(String id) {
		ImageView fleche = new ImageView(images.get("squelette"));
		fleche.setId(id);
		return fleche;
	}
	public ImageView creerBoss(String id) {
		ImageView fleche = new ImageView(images.get("Boss"));
		fleche.setId(id);
		return fleche;
	}
	public ImageView creerBouleDeFeu(String id) {
		ImageView fleche = new ImageView(images.get("BouleDeFeu"));
		fleche.setId(id);
		return fleche;
	}
	public ImageView creerOnde(String id) {
		ImageView fleche = new ImageView(images.get("onde"));
		fleche.setId(id);
		return fleche;
	}
}
