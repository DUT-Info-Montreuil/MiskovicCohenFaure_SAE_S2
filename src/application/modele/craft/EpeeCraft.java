package application.modele.craft;

import application.modele.Joueur;

public class EpeeCraft extends OutilCraft {

	public EpeeCraft(Joueur j) {
		super(j);
		this.setOutilCase(j.getInventaire().getlisteCaseInventaireAvecIndex(0));
		
	}
	
	@Override
	public boolean craft() {
		int indMax = getOutilCase().getIndexMax();
		if (indMax !=4) 
			if (getMateriauxCompteur().get(0).getMat() >= 1 && getMateriauxCompteur().get(indMax).getMat() >= 2) {
				getMateriauxCompteur().get(0).retirerMat(1);
				getMateriauxCompteur().get(indMax).retirerMat(2);
				this.getOutilCase().augmenterIndexMax();
				return true;
			}
		return false;
	}

}
