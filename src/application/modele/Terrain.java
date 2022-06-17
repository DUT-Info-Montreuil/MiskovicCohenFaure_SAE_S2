package application.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Terrain {
	private int table[];

	public Terrain() {
		table = new int[240*33];
		try {

			Reader reader;
			reader = new FileReader("/home/etudiants/info/hcohen/git/SaEDev_S2/src/application/ressource/map");
			BufferedReader br = new BufferedReader(reader, 16384);

			String line = null;
			int indTableau = 0;
			while((line = br.readLine())!= null) {
				for (int i=0; i< line.length();i++) {
					if (line.charAt(i) == ',')
						indTableau++;
					else 
						table[indTableau]= line.charAt(i) - '0';
				}
			}
			br.close();
			
//			for (int i=0;i<table.length;i++) {
//				if (i%240==0)
//					System.out.println();
//				System.out.print(table[i]);
//			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("Fichier Non Trouvé");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] getTable() {
		return table;
	}

	public void supprimerCase(int i) {
		table[i]=0;
	}
	
	public void changerCase(int i, int bloc) {
		table[i] = bloc;
	}
}
