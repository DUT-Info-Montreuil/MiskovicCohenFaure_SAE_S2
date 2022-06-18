package application.vue;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TexteTutoVue {

	private Label txt;
	
	public TexteTutoVue (Pane terrainPane) {
		this.txt=new Label();
		terrainPane.getChildren().add(txt);
		this.txt.setTranslateX(500);
		this.txt.setTranslateY(500);
	}
	
	public Label getTxt() {
		return this.txt;
	}
}
