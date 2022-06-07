
package application.modele;

public class Outils {

	public static int coordToTile (int x,int y) {
		if(x<0 || x>310  ||
				y<-75    ||  y>280){
			return -1;
		}

		else {
			x=x/32;
			if (y>0) {
				y=y/32;
			}
			else {
				y=-1;
			}
			return (x+(y+1)*10);
		}

	}


}