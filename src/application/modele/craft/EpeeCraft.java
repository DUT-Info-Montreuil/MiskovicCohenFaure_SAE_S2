package application.modele.craft;

import application.modele.Joueur;

public class EpeeCraft extends OutilCraft {

	public EpeeCraft(Joueur j) {
		super(j);
		this.setOutilCase(j.getInventaire().getlisteCaseInventaireAvecIndex(0));
		
	}
	
	@Override
	public void craft() {
		int indMax = getOutilCase().getIndexMax();
		if (indMax !=3) 
			if (getMateriauxCompteur().get(0).getMat() >= 1 && getMateriauxCompteur().get(indMax+1).getMat() >= 2) {
				getMateriauxCompteur().get(0).retirerMat(1);
				getMateriauxCompteur().get(indMax+1).retirerMat(2);
				this.getOutilCase().augmenterIndexMax();
			}
	}

}
