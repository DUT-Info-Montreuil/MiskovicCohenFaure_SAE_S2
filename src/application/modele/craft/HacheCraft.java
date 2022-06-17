package application.modele.craft;

import application.modele.Joueur;

public class HacheCraft extends OutilCraft {

	public HacheCraft(Joueur j) {
		super(j);
		this.setOutilCase(j.getInventaire().getlisteCaseInventaireAvecIndex(2));
	}
	
	@Override
	public void craft() {
		int indMax = getOutilCase().getIndexMax();
		if (indMax !=3) 
			if (getMateriauxCompteur().get(0).getMat() >= 1 && getMateriauxCompteur().get(indMax+1).getMat() >= 3) {
				getMateriauxCompteur().get(0).retirerMat(1);
				getMateriauxCompteur().get(indMax+1).retirerMat(2);
				this.getOutilCase().augmenterIndexMax();
			}
	}

}