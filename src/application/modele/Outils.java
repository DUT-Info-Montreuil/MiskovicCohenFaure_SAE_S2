
package application.modele;

public class Outils {

	//Recupère coordonnée et renvoie numéro de tuile
	public static int coordToTile (double oldX,double oldY) {

		int x=(int)oldX;
		int y=(int)oldY;

		if(x<0 || y<-75 )
			return -1;

		else {
			x=x/32;
			if (y>0) {
				y=y/32;
			}
			else {
				y=-1;
			}
			return (x+(y+1)*240);
		}
	}

	//Renvoie vrai si coordonnée dans la range du joueur
	public static boolean verifRange(double xJoueur, double yJoueur, int numeroCase) {
		int caseJoueur = coordToTile(xJoueur+16, yJoueur);
		return ( (caseJoueur - numeroCase < 5 && caseJoueur - numeroCase > -5) 
				|| ( (caseJoueur-240) - numeroCase < 5 && (caseJoueur-240) - numeroCase > -5) 
				|| ( (caseJoueur+240) - numeroCase < 5 && (caseJoueur+240) - numeroCase > -5) );
	}

	//Renvoie vrai si la case est celle sur laquelle le joueur est
	public static boolean memeTile(double xJoueur, double yJoueur, int numeroCase) {
		int caseJoueur = coordToTile(xJoueur+16, yJoueur);
		return (caseJoueur - numeroCase < 1 && caseJoueur - numeroCase > -1);
	}






}