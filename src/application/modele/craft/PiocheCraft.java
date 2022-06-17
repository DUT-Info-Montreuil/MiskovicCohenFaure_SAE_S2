package application.modele.craft;

import application.modele.Joueur;

public class PiocheCraft extends OutilCraft {

	public PiocheCraft(Joueur j) {
		super(j);
		this.setOutilCase(j.getInventaire().getlisteCaseInventaireAvecIndex(1));
	}

	@Override
	public boolean craft() {
		int indMax = getOutilCase().getIndexMax();
		if (indMax !=4) 
			if (getMateriauxCompteur().get(0).getMat() >= 1 && getMateriauxCompteur().get(indMax).getMat() >= 3) {
				getMateriauxCompteur().get(0).retirerMat(1);
				getMateriauxCompteur().get(indMax).retirerMat(2);
				this.getOutilCase().augmenterIndexMax();
				return true;
			}
		return false;
	}

}
