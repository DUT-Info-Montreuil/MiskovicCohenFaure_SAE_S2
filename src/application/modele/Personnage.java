package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

	//Déplacement
	private DoubleProperty coordXProperty, coordYProperty;
	private DoubleProperty dirGauche;
	private DoubleProperty dirDroite; 
	private double dirY;
	
	//PV
	private IntegerProperty pvProperty;

	private int pvMax;
	
	//Taille Sprite		(si une valeur est negative alors sa valeur absolue soit etre inferieur a celle de son opposé)
	private int tHaut,tBas,tDroite,tGauche;

	//Terrain
	private Environnement env;
	
	//Id
	private static int compteur=0;
	private String id;

	public Personnage (double coordX, double coordY, int pvMax,Environnement e,int h, int d, int b, int g) {
		this.coordXProperty = new SimpleDoubleProperty(coordX);
		this.coordYProperty = new SimpleDoubleProperty(coordY);
		this.dirGauche=new SimpleDoubleProperty(0);
		this.dirDroite=new SimpleDoubleProperty(0);
		dirY = 0;
		
		this.pvMax = pvMax;
		pvProperty = new SimpleIntegerProperty(pvMax);
		
		this.env=e;
		
		this.tHaut=h;
		this.tDroite=d;
		this.tBas=b;
		this.tGauche=g;
		
		id="Perso" + compteur;
		compteur++;
//		this.sprite=new ImageView();
//		this.sprite.setImage(image);	
	}

	

	//Direction
	public void move () {
		this.coordXProperty.set(coordXProperty.get() + this.dirDroite.get() - this.dirGauche.get());
		this.coordYProperty.set(coordYProperty.get() + dirY);
	}
	public void additionnerDirY(double d) {
		this.dirY += d;
	}

	//PV
	public void perdrePV(int valeur,boolean versDroite) {
		pvProperty.set(pvProperty.get() - valeur);
		if (versDroite)
			this.setDirDroite(4);
		else 
			this.setDirGauche(4);
		this.setDirY(-5);
	}

	public void meurt () {
		if (this.pvProperty.get()<=0) {
			this.getEnv().retirerMob(this);
		}
	}
	public void ajouterPV(int valeur) {
		if (pvProperty.get() + valeur > pvMax) 
			pvProperty.set(pvMax);
		else
			pvProperty.set(pvProperty.get() + valeur);
	}
	public boolean estMort() {
		return pvProperty.get() <= 0;
	}

	//Setter & Getter
	public int getPv() {
		return pvProperty.get();
	}
	public IntegerProperty pvProperty() {
		return pvProperty;
	}
	public int getPvMax() {
		return pvMax;
	}
	public String getId() {
		return id;
	}
	public double getDirGauche() {
		return this.dirGauche.get();
	}
	public DoubleProperty getDirGaucheProperty() {
		return this.dirGauche;
	}
	public Environnement getEnv() {
		return env;
	}
	public void setDirGauche(double d) {
		this.dirGauche.set(d);
	}
	public double getDirDroite() {
		return this.dirDroite.get();
	}
	public DoubleProperty getDirDroiteProperty() {
		return this.dirDroite;
	}
	public void setDirDroite(double d) {
		this.dirDroite.set(d);
	}
	public DoubleProperty xProperty() {
		return coordXProperty;
	}
	public DoubleProperty yProperty() {
		return coordYProperty;
	}
	public double getX () {
		return this.coordXProperty.getValue();
	}
	public double getY () {
		return this.coordYProperty.getValue();
	}
	public double getDirY() {
		return dirY;
	}
	public void setDirY(double dirY) {
		this.dirY = dirY;
	}
	
	public int getThaut() {
		return tHaut;
	}

	public int gettBas() {
		return tBas;
	}

	public int gettDroite() {
		return tDroite;
	}

	public int gettGauche() {
		return tGauche;
	}
	
	public void setPvProperty(int pvProperty) {
		this.pvProperty.set(pvProperty);
	}

	
	//Gestion de l'inertie
	public void inertie() {
		if (this.dirDroite.get()>0) {
			this.setDirDroite(this.dirDroite.get()-0.2);
		}
		if (this.dirGauche.get()>0) {
			this.setDirGauche(this.dirGauche.get()-0.2);
		}
	}
	


	//Collisions
	public void gestionCollision () {
		double x = this.coordXProperty.get();
		double y = this.coordYProperty.get();
		collisionDroite(x,y);
		collisionGauche(x,y);
		collisionHaut(x,y);
		collisionBas();

	}
	public boolean collisionDroite (double x,double y) {
		//verifie si le joueur est est en contact avec un bloc pour l'arreter
		if (checkCollision(Outils.coordToTile(x+this.tDroite, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x+this.tDroite, y+this.tBas-3), this.env)) {
			//verifie si le joueur est dans un bloc pour le sortir
			if (checkCollision(Outils.coordToTile(x+this.tDroite-1, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x+this.tDroite-1, y+this.tBas-3), this.env)) {
				this.coordXProperty.set((int) (x-this.dirDroite.get())-1);
			}
			else {
				this.coordXProperty.set((int) (x-this.dirDroite.get()));
			}
			return true;
		}
		return false;
	}
	public boolean collisionGauche (double x,double y) {
		//verifie si le jougetLargeureur est est en contact avec un bloc pour l'arreter
		if (checkCollision(Outils.coordToTile(x-this.tGauche, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x-this.tGauche, y+this.tBas-3), this.env)) {
			//verifie si le joueur est dans un bloc pour le sortir
			if (checkCollision(Outils.coordToTile(x-this.tGauche+1, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x-this.tGauche+1, y+this.tBas-3), this.env)) {
				this.coordXProperty.set((int) (x+this.dirGauche.get())+1);
			}
			else {
				this.coordXProperty.set((int) (x+this.dirGauche.get()));
			}
			return true;
		}
		return false;
	}
	public void collisionHaut (double x,double y) {
		//verifie si le joueur est est en contact avec un bloc pour l'arreter
		if (checkCollision(Outils.coordToTile(x+this.tDroite-3, y-this.tHaut), this.env)||checkCollision(Outils.coordToTile(x-this.tGauche+3, y-this.tHaut), this.env)) {
			this.setDirY(1);
		}
	}
	public boolean collisionBas () {
		double x=this.coordXProperty.get();
		double y=this.coordYProperty.get();
		//verifie si le joueur est est en contact avec un bloc pour l'arreter
		if (checkCollision(Outils.coordToTile(x+this.tDroite-2 ,y+1+this.tBas), this.env)||checkCollision(Outils.coordToTile(x-this.tGauche+2, y+1+this.tBas), this.env)) {
			//verifie si le joueur est dans un bloc pour le sortir
			if (checkCollision(Outils.coordToTile(x+this.tDroite-2 ,y+this.tBas), this.env)||checkCollision(Outils.coordToTile(x-this.tGauche+2, y+this.tBas), this.env)) {
				this.coordYProperty.set(this.coordYProperty.get()-1);
			}
			if (this.getDirY()>0 || this.getDirY()==-1) {
				this.setDirY(0);
			}
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkCollision (int x,Environnement e) {
		if (x<e.getTerrain().getTable().length) {
			return (x<0 || e.getTerrain().getTable()[x]>0);
		}
		else {
			return true;
		}
	}
	
	public void action() {
		this.gestionCollision();
		this.inertie();
		this.move();
		this.gravite();
		this.meurt();
	}
	
	public void gravite() {
		if (!this.collisionBas()) {
			if(this.getDirY() < 5)
				this.additionnerDirY(0.5);	
		}
	}
}
