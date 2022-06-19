package application.vue.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.items.*;
import application.modele.items.utilitaires.*;

public class InventaireTest {
	Environnement env = new Environnement();
	Inventaire inv = this.env.getJoueur().getInventaire();

	@Test
	final void changementItem() {
		//Main vide
		assertEquals(null, inv.itemEnMain());
		
		inv.itemPrecedent();
		
		//Main vide meme après changement d'item
		assertEquals(null, inv.itemEnMain());

		inv.itemSuivant();

		//Epee en pierre
		assertEquals("class application.modele.items.utilitaires.Epee", inv.itemEnMain().getClass().toString());
		assertTrue(((Epee)inv.itemEnMain()).getMateriaux() == 0);

		//Case suivante
		inv.changerCase(1);

		//Main vide
		assertEquals(null, inv.itemEnMain());

		inv.itemSuivant();

		//Pioche en pierre
		assertEquals("class application.modele.items.utilitaires.Pioche", inv.itemEnMain().getClass().toString());
		assertTrue(((Pioche)inv.itemEnMain()).getMateriaux() == 0);

		//Case suivante
		inv.changerCase(2);

		//Main vide
		assertEquals(null, inv.itemEnMain());

		inv.itemSuivant();

		//Hache en pierre
		assertEquals("class application.modele.items.utilitaires.Hache", inv.itemEnMain().getClass().toString());
		assertTrue(((Hache)inv.itemEnMain()).getMateriaux() == 0);

		//Case suivante
		inv.changerCase(3);

		//Main vide
		assertEquals(null, inv.itemEnMain());

		inv.itemSuivant();

		//Arc
		assertEquals("class application.modele.items.utilitaires.Arc", inv.itemEnMain().getClass().toString());

		//Case suivante
		inv.changerCase(4);

		//Main vide
		assertEquals(null, inv.itemEnMain());
		
		inv.itemSuivant();
		
		//Main vide meme après changement d'item
		assertEquals(null, inv.itemEnMain());
	}
	
	@Test
	final void gestionBlocs() {
		//Ici on ne pourra ajouter que des blocs a l'inventaire, car les autres items sont deja ajoutes par defaut. C'est indexMax dans ListeCaseInventaire qui en gere leur accessiblite.
		
		inv.ajouterItem(new Bloc(0));
		
		inv.changerCase(4);
		
		inv.itemSuivant();
		
		assertEquals("B0", inv.idItemEnMain());
		
		inv.ajouterItem(new Bloc(5));
		
		inv.itemSuivant();
		
		assertEquals("B5", inv.idItemEnMain());
		
		inv.ajouterItem(new Bloc(5));
		
		assertTrue(((Bloc)inv.itemEnMain()).getQuantite() == 2);
		
		inv.poserBloc("B5");
		
		assertTrue(((Bloc)inv.itemEnMain()).getQuantite() == 1);
		
		inv.poserBloc("B5");
		
		assertEquals("B0", inv.idItemEnMain());
		
		inv.poserBloc("B0");
		
		assertEquals(null, inv.itemEnMain());
	}
}
