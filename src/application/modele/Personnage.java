package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

	//Déplacement
	private DoubleProperty coordXProperty, coordYProperty;
	private DoubleProperty dirGaucheProperty;
	private DoubleProperty dirDroiteProperty; 
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

	public Personnage (double coordX, double coordY, int pvMax, Environnement e, int h, int d, int b, int g) {
		//Initialisation Coordonnées et Direction
		this.coordXProperty = new SimpleDoubleProperty(coordX);
		this.coordYProperty = new SimpleDoubleProperty(coordY);
		this.dirGaucheProperty=new SimpleDoubleProperty(0);
		this.dirDroiteProperty=new SimpleDoubleProperty(0);
		dirY = 0;
		
		//Initialisation PV
		this.pvMax = pvMax;
		pvProperty = new SimpleIntegerProperty(pvMax);
		
		//Initialisation Environnement
		this.env=e;
		
		//Initialisation taille sprite
		this.tHaut=h;
		this.tDroite=d;
		this.tBas=b;
		this.tGauche=g;
		
		//Gestion ID
		id="Perso" + compteur;
		compteur++;	
	}

	//MOUVEMENT
	public void move () {
		this.coordXProperty.set(coordXProperty.get() + this.dirDroiteProperty.get() - this.dirGaucheProperty.get());
		this.coordYProperty.set(coordYProperty.get() + dirY);
	}
	
	public void additionnerDirY(double d) {
		this.dirY += d;
	}

	//PV
	public void ajouterPV(int valeur) {
		if (pvProperty.get() + valeur > pvMax) 
			pvProperty.set(pvMax);
		else
			pvProperty.set(pvProperty.get() + valeur);
	}
	public void perdrePV(int valeur,boolean versDroite) {
		pvProperty.set(pvProperty.get() - valeur);
		if (versDroite)
			this.setDirDroite(4);
		else 
			this.setDirGauche(4);
		this.setDirY(-5);
	}
	
	public void perdrePV(int valeur) {
		pvProperty.set(pvProperty.get() - valeur);
	}

	public void meurt () {
		if (this.pvProperty.get()<=0) {
			this.getEnv().retirerMob(this);
		}
	}
	public boolean estMort() {
		return pvProperty.get() <= 0;
	}
	
	//INERTIE
	public void inertie() {
		if (this.dirDroiteProperty.get()>0) {
			this.setDirDroite(this.dirDroiteProperty.get()-0.2);
		}
		if (this.dirGaucheProperty.get()>0) {
			this.setDirGauche(this.dirGaucheProperty.get()-0.2);
		}
	}

	//COLLISIONS
	public void gestionCollision () {
		double x = this.coordXProperty.get();
		double y = this.coordYProperty.get();
		collisionDroite(x,y);
		collisionGauche(x,y);
		collisionHaut(x,y);
		collisionBas();
	}
	
	public boolean checkCollision (int x,Environnement e) {
		if (x<e.getTerrain().getTable().length) {
			return (x<0 || e.getTerrain().getTable()[x]>0);
		}
		else {
			return true;
		}
	}
	
	public boolean collisionDroite (double x,double y) {
		//verifie si le joueur est est en contact avec un bloc pour l'arreter
		if (checkCollision(Outils.coordToTile(x+this.tDroite, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x+this.tDroite, y+this.tBas-3), this.env)) {
			//verifie si le joueur est dans un bloc pour le sortir
			if (checkCollision(Outils.coordToTile(x+this.tDroite-1, y-this.tHaut+5), this.env)||checkCollision(Outils.coordToTile(x+this.tDroite-1, y+this.tBas-3), this.env)) {
				this.coordXProperty.set((int) (x-this.dirDroiteProperty.get())-1);
			}
			else {
				this.coordXProperty.set((int) (x-this.dirDroiteProperty.get()));
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
				this.coordXProperty.set((int) (x+this.dirGaucheProperty.get())+1);
			}
			else {
				this.coordXProperty.set((int) (x+this.dirGaucheProperty.get()));
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
	
	//GRAVITE
	public void gravite() {
		if (!this.collisionBas()) {
			if(this.dirY < 5)
				this.additionnerDirY(0.5);	
		}
	}
	
	//ACTION PERSONNAGE
	public void action() {
		this.gestionCollision();
		this.inertie();
		this.move();
		this.gravite();
		this.meurt();
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
			return this.dirGaucheProperty.get();
		}
		public DoubleProperty getDirGaucheProperty() {
			return this.dirGaucheProperty;
		}
		public Environnement getEnv() {
			return env;
		}
		public void setDirGauche(double d) {
			this.dirGaucheProperty.set(d);
		}
		public double getDirDroite() {
			return this.dirDroiteProperty.get();
		}
		public DoubleProperty getDirDroiteProperty() {
			return this.dirDroiteProperty;
		}
		public void setDirDroite(double d) {
			this.dirDroiteProperty.set(d);
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
		public void setX (double x) {
			this.coordXProperty.set(x);
		}
		public void setY (double y) {
			this.coordYProperty.set(y);
		}
		public double getDirY() {
			return dirY;
		}
		public void setDirY(double dirY) {
			this.dirY = dirY;
		}
		public int getTHaut() {
			return tHaut;
		}
		public int getTBas() {
			return tBas;
		}
		public int getTDroite() {
			return tDroite;
		}
		public int gettGauche() {
			return tGauche;
		}
		public void setPvProperty(int pvProperty) {
			this.pvProperty.set(pvProperty);
		}
		public void setPv(int pv) {
			this.pvProperty.set(pv);
		}
}
