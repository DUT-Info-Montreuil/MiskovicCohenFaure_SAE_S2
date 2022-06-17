package application.modele.craft.materiaux;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Materiaux {
	
	private IntegerProperty matProperty;
	
	public Materiaux () {
		this.matProperty = new SimpleIntegerProperty(0);
	}
	
	public int getMat() {
		return this.matProperty.get();
	}

	public IntegerProperty matProperty() {
		return this.matProperty;
	}
	
	public void ajouterMat(int nb) {
		this.matProperty.set(matProperty.get()+nb);
	}
	
	public void retirerMat(int nb) {
		this.matProperty.set(matProperty.get()-nb);
	}

}
