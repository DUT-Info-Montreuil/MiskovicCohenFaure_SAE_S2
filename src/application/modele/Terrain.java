package application.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Terrain {
	private int table[];

	public Terrain() {
		//Création du tableau qui stocke le terrain
		table = new int[240*32];
		
		//Lecture du fichier
		try {

			Reader reader;
			reader = new FileReader("src/application/ressource/map");
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
