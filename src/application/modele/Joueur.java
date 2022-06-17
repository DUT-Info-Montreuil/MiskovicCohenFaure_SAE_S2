package application.modele;

import java.util.ArrayList;

import application.modele.craft.EpeeCraft;
import application.modele.craft.HacheCraft;
import application.modele.craft.OutilCraft;
import application.modele.craft.PiocheCraft;
import application.modele.craft.materiaux.Bois;
import application.modele.craft.materiaux.Diamant;
import application.modele.craft.materiaux.Fer;
import application.modele.craft.materiaux.Materiaux;
import application.modele.craft.materiaux.Or;
import application.modele.items.utilitaires.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Joueur extends Personnage{

	private Inventaire inventaire;
	private ArrayList<Materiaux> compteurMateriaux;
	//0:Bois 1:Fer 2:Or 3:Diamant

	private OutilCraft epee, hache, pioche;

	private boolean clickQ, clickD;
	private int iFrame;
	private boolean versDroite ;

	public Joueur(int coordX, int coordY,Environnement e) {
		super(coordX, coordY,5,e,20,25,0,-9);

		this.clickD=false;
		this.clickQ=false;
		this.iFrame=0;
		this.versDroite=true;
		this.inventaire = new Inventaire();
		initItem();
		this.compteurMateriaux = new ArrayList<Materiaux>();
		initCompteurMateriaux();

		initCraft();

		this.xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				versDroite=((double)oldValue<(double)newValue);
			}	
		});
	}
	public ArrayList<Materiaux> getCompteurMateriaux() {
		return compteurMateriaux;
	}

	public void initItem() {
		for (int i=0; i<4; i++) {
			this.inventaire.ajouterItem(new Pioche(i));
			this.inventaire.ajouterItem(new Epee(i));
			this.inventaire.ajouterItem(new Hache(i));
		}
		this.inventaire.ajouterItem(new Arc(0));
	}

	public void initCraft() {
		this.epee = new EpeeCraft(this);
		this.hache  = new HacheCraft(this);
		this.pioche  = new PiocheCraft(this);
	}

	public void initCompteurMateriaux() {
		Materiaux bois = new Bois();
		this.compteurMateriaux.add(bois);
		Materiaux fer = new Fer();
		this.compteurMateriaux.add(fer);
		Materiaux or = new Or();
		this.compteurMateriaux.add(or);
		Materiaux diamant = new Diamant();
		this.compteurMateriaux.add(diamant);
	}
	public void gravite() {
		if (!this.collisionBas()) {
			if(this.getDirY() < 5)
				this.additionnerDirY(1);	
		}
	}

	public void saut() {
		if (this.collisionBas()) {
			this.additionnerDirY(-10);
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
				if (this.getX()+this.getTDroite()<e.getX()+e.getTDroite()
				&& this.getX()+this.getTDroite()+20>e.getX()-e.gettGauche()
				&& this.getY()+this.getTBas()>e.getY()-e.getTHaut()
				&& this.getY()-this.getTHaut()<e.getY()+e.getTBas()  ) {
					e.perdrePV(1, versDroite);
				}
			}
		}
		else {
			for (int i=this.getEnv().getMobs().size()-1;i>=0;i--) {
				e=this.getEnv().getMobs().get(i);
				if (this.getX()-this.gettGauche()>e.getX()-e.gettGauche()
						&& this.getX()-this.gettGauche()-20<e.getX()+e.getTDroite()
						&& this.getY()+this.getTBas()>e.getY()-e.getTHaut()
						&& this.getY()-this.getTHaut()<e.getY()+e.getTBas() ) {
					e.perdrePV(1, versDroite);
				}
			}
		}	
	}
	public OutilCraft getEpee() {
		return epee;
	}
	public OutilCraft getHache() {
		return hache;
	}
	public OutilCraft getPioche() {
		return pioche;
	}


}
