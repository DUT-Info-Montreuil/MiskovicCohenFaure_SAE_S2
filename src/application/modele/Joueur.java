package application.modele;

import application.modele.items.utilitaires.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Joueur extends Personnage{
	
	Inventaire inventaire;
	private boolean clickQ, clickD;
	private int iFrame;
	private boolean versDroite ;
	
	public Joueur(int coordX, int coordY,Environnement e) {
		super(coordX, coordY,5,e,20,25,0,-9);
		
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
		
	
	public void gravite() {
		if (!this.collisionBas()) {
			if(this.getDirY() < 5)
				this.additionnerDirY(1);	
		}
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
	
	public boolean isVersDroite() {
		return versDroite;
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
			for (int i=this.getEnv().getMobs().size()-1;i>=0;i--) {
				e=this.getEnv().getMobs().get(i);
				if (this.getX()+this.gettDroite()<e.getX()+e.gettDroite()
						&& this.getX()+this.gettDroite()+20>e.getX()-e.gettGauche()
						&& this.getY()+this.gettBas()>e.getY()-e.getThaut()
						&& this.getY()-this.getThaut()<e.getY()+e.gettBas()  ) {
					e.perdrePV(1, versDroite);
				}
			}
		}
		else {
			for (int i=this.getEnv().getMobs().size()-1;i>=0;i--) {
				e=this.getEnv().getMobs().get(i);
				if (this.getX()-this.gettGauche()>e.getX()-e.gettGauche()
						&& this.getX()-this.gettGauche()-20<e.getX()+e.gettDroite()
						&& this.getY()+this.gettBas()>e.getY()-e.getThaut()
						&& this.getY()-this.getThaut()<e.getY()+e.gettBas() ) {
					e.perdrePV(1, versDroite);
				}
			}
		}	
	}
}
