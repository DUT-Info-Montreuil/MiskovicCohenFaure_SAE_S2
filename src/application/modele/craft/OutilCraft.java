package application.modele.craft;

import java.util.ArrayList;

import application.modele.Joueur;
import application.modele.ListeCaseInventaire;
import application.modele.craft.materiaux.Materiaux;

public abstract class OutilCraft {

	private ArrayList<Materiaux> materiauxCompteur;
	private ListeCaseInventaire outilCase;
	
	public OutilCraft (Joueur j) {
		this.materiauxCompteur = j.getCompteurMateriaux();
	}
	
	public abstract void craft();

	public void setOutilCase(ListeCaseInventaire indexMaxMateriaux) {
		this.outilCase = indexMaxMateriaux;
	}

	public ArrayList<Materiaux> getMateriauxCompteur() {
		return materiauxCompteur;
	}
	public ListeCaseInventaire getOutilCase() {
		return outilCase;
	}
}
