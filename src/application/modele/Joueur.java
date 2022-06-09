package application.modele;

import application.modele.Inventaire;
import application.modele.items.utilitaires.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import application.modele.items.Bloc;

public class Joueur extends Personnage{
	
	Inventaire inventaire;
	private boolean clickQ, clickD;
	private int iFrame;
	private boolean versDroite ;
	

	public Joueur(int coordX, int coordY,Environnement e) {
		super(coordX, coordY,5,e,23,25);
		this.clickD=false;
		this.clickQ=false;
		this.iFrame=0;
		this.versDroite=true;
		inventaire = new Inventaire();
		inventaire.ajouterItem(new Pioche(0));
		inventaire.ajouterItem(new Epee(0));
		inventaire.ajouterItem(new Hache(0));
		inventaire.ajouterItem(new Arc(0));
		
		this.xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				versDroite=((double)oldValue<(double)newValue);
			}	
		});
	}
	
	public void saut() {
		if (this.collisionBas()) {
			super.additionnerDirY(-10);
		}
	}
	
	public Inventaire getInventaire() {
		return inventaire;
	}
	
	public void setClickQ(boolean clickQ) {
		this.clickQ = clickQ;
	}

	public void setClickD(boolean clickD) {
		this.clickD = clickD;
	}
	
	//Gestion de l'inertie
	public void inertie() {
		double acceleration = 0.25;
		int Vmax = 3;
		if(this.clickD) {
			if (this.getDirDroite()<Vmax){
				this.setDirDroite(this.getDirDroite()+acceleration);
			}
		}
		else {
			if (this.getDirDroite()>0) {
				this.setDirDroite(this.getDirDroite()-acceleration);
			}
		}
		if (this.clickQ) {
			if (this.getDirGauche()<Vmax) {
				this.setDirGauche(this.getDirGauche()+acceleration);
			}
		}
		else {
			if (this.getDirGauche()>0) {
				this.setDirGauche(this.getDirGauche()-acceleration);
			}
		}
	}
	public void action () {
		super.action();
		if (this.iFrame!=0) {
			this.iFrame--;
		}
	}
	
	public void perdrePV(int valeur,boolean versDroite) {
		if (this.iFrame==0) {
			super.perdrePV(valeur, versDroite);
			this.iFrame=50;
		}
	}
	
	public void attaque () {
		Personnage e;
		if (this.versDroite) {
			for (int i=this.getEnv().getPersos().size()-1;i>=0;i--) {
				e=this.getEnv().getMobs().get(i);
				if (this.getX()+this.getLargeur()<e.getX()+e.getLargeur()
						&& this.getX()+this.getLargeur()+20>e.getX()
						&& this.getY()<e.getY()+e.getHauteur()
						&& this.getY()+this.getHauteur()>e.getY() ) {
					e.perdrePV(1, versDroite);
				}
			}
		}
		else {
			for (int i=this.getEnv().getPersos().size()-1;i>=0;i--) {
				e=this.getEnv().getMobs().get(i);
				if (this.getX()>e.getX() 
						&& this.getX()-20<e.getX()+e.getLargeur()
						&& this.getY()<e.getY()+e.getHauteur()
						&& this.getY()+this.getHauteur()>e.getY() ) {
					e.perdrePV(1, versDroite);
				}
			}
		}
	}
}
