package application.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Terrain {
	private int table[];

	public Terrain() {
		table = new int[120*33];
		try {

			Reader reader;
			reader = new FileReader("/home/etudiants/info/hcohen/git/SaEDev_S2/src/application/ressource/testTerrain");
			BufferedReader br = new BufferedReader(reader, 16384);

			String line = null;
			int indTableau = 0;
			while((line = br.readLine())!= null) {
				for (int i=0; i< line.length();i++) {
					if (line.charAt(i) == ',')
						indTableau++;
					else /*if (table[indTableau] == 0)*/
						table[indTableau]=line.charAt(i) - '0';
					/*else
						table[indTableau]= (table[indTableau]*10) + line.charAt(i) - '0';*/
				}
			}
			br.close();
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
}
