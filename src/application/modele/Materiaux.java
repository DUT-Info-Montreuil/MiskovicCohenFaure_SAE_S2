package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Materiaux {
	
	private IntegerProperty ferProperty, orProperty, diamantProperty;
	
	public Materiaux () {
		this.ferProperty = new SimpleIntegerProperty(0);
		this.orProperty = new SimpleIntegerProperty(0);
		this.diamantProperty = new SimpleIntegerProperty(0);
	}
	
	public int getFer() {
		return this.ferProperty.get();
	}
	public int getOr() {
		return this.orProperty.get();
	}
	public int getDiamant() {
		return this.diamantProperty.get();
	}

	
	public IntegerProperty ferProperty() {
		return this.ferProperty;
	}
	public IntegerProperty orProperty() {
		return this.orProperty;
	}
	public IntegerProperty diamantProperty() {
		return this.diamantProperty;
	}
	
	
	public void ajouterFer() {
		this.ferProperty.set(ferProperty.get()+1);
	}
	public void ajouterOr() {
		this.orProperty.set(orProperty.get()+1);
	}
	public void ajouterDiamant() {
		this.diamantProperty.set(diamantProperty.get()+1);
	}

}
