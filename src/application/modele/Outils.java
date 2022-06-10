
package application.modele;

public class Outils {

	public static int coordToTile (double oldX,double oldY) {
		int x=(int)oldX;
		int y=(int)oldY;
		if(x<0 || /*x>340  ||*/
				y<-75    /*||  y>340*/){
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
			return (x+(y+1)*120);
		}
	}
	

	
	


}