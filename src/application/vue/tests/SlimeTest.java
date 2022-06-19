package application.vue.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import application.modele.*;
import application.modele.mobs.Slime;

class SlimeTest {
	Environnement env = new Environnement();
	Joueur j=this.env.getJoueur();
	Slime s = new Slime (200,200,env);

	
	@Test
	final void testAttaquer() {
		
		s.setX(200);
		s.setY(200);
		j.setX(200);
		j.setY(200);
		
		s.attaque();
		//cas où le joueur touche le slime
		assertEquals(4,j.getPv());
		
		j.setPv(5);
		
		j.setX(300);
		j.setY(300);
		
		s.attaque();
		//cas où le joueur ne touche pas le slime
		assertEquals(5,j.getPv());
		}
	
	@Test
	final void testMove() {
		double x = s.getX();
		
		//test deplacement droite
		s.setDirDroite(2);
		s.setDirGauche(0);
		s.move();
		
		assertTrue(s.getX()>x);
		
		x = s.getX();
		
		//test deplacement gauche
		s.setDirGauche(2);
		s.setDirDroite(0);
		s.move();
		
		assertTrue(s.getX()<x);

		
	}
	
	@Test
	final void testGravite() {
		s.setY(0);
		double y = s.getY();
		
		s.gravite();
		s.move();

		assertTrue(s.getY()>y);
	}
	
	@Test
	final void testInertie() {
		
		while (!s.collisionBas()) {
			s.gravite();
			s.move();
		}
		
		double dir;
		
		//test inertie droite
		s.setDirDroite(2);
		s.setDirGauche(0);
		dir = s.getDirDroite();
		
		s.inertie();
		
		assertTrue(s.getDirDroite()<dir);
		
		//test inertie gauche
		s.setDirGauche(2);
		s.setDirDroite(0);
		dir = s.getDirGauche();
		
		s.inertie();
		
		assertTrue(s.getDirGauche()<dir);

		
	}
	
	
	@Test
	final void testcollision() {
		//bas
		//placement du slime dans une zone vide
		s.setY(0);
		s.setX(300);
		//placment d'un bloc une tile sous le slime
		env.getTerrain().getTable()[Outils.coordToTile(300, 34)]=1;
		
		while (!s.collisionBas()) {
			s.gravite();
			s.move();
		}
		
		assertEquals(Outils.coordToTile(s.getX(), s.getY()), Outils.coordToTile(300, 34));
		
		
		//gauche
		//placement du slime
		env.getTerrain().getTable()[Outils.coordToTile(300, 34)]=0;
		while (!s.collisionBas()) {
			s.gravite();
			s.move();
		}
		
		//deplacement du slime a gauche
		while (!s.collisionGauche(s.getX(),s.getY())) {
			s.setDirGauche(2);
			s.gravite();
			s.move();
		}
		
		//verifie si son bloc a gauche est un bloc
		assertTrue(Outils.coordToTile(s.getX(), s.getY()-2)>0);
		
		//droite

		//deplacement du slime a gauche
		while (!s.collisionDroite(s.getX(),s.getY())) {
			s.setDirDroite(2);
			s.gravite();
			s.move();
		}
		
		//verifie si son bloc a gauche est un bloc
		assertTrue(Outils.coordToTile(s.getX(), s.getY()-2)>0);

	}
	
	@Test
	final void testDeplacement() {
		//a droite
		s.setDirDroite(0);
		s.setDirGauche(0);
		s.setX(300);
		s.setY(300);
		
		j.setX(400);
		j.setY(300);
		
		s.detection();
		s.move();
		
		assertEquals((int)s.getDirDroite(), 0);
		assertEquals((int)s.getDirY(), 0);	
		
		for (int i=0 ; i<101 ; i++) {
			s.detection();
			s.move();
		}
		
		assertTrue(s.getDirDroite()>0);
		assertTrue(s.getDirY()<0);
		
		//a gauche
		s=new Slime(300, 300, env);
		
		j.setX(200);
		j.setY(300);
		
		s.detection();
		s.move();
		
		assertEquals((int)s.getDirGauche(), 0);	
		assertEquals((int)s.getDirY(), 0);	
		
		for (int i=0 ; i<101 ; i++) {
			s.detection();
			s.move();
		}
		
		assertTrue(s.getDirGauche()>0);
		assertTrue(s.getDirY()<0);
		
	}
	
	@Test
	final void testDegats() {
		s.setDirDroite(0);
		s.setDirGauche(0);
		s.setDirY(0);
		
		int pv = s.getPv();
		double d = s.getDirDroite();
		double g = s.getDirGauche();
		double y = s.getDirY();
		
		s.perdrePV(1, true);
		assertEquals(pv-1 , s.getPv());
		assertTrue(d<(int)s.getDirDroite());	
		assertTrue(y>(int)s.getDirY());
		
		s.setDirDroite(0);
		s.setDirGauche(0);
		s.setDirY(0);
		
		s.setPv(s.getPvMax());
		pv = s.getPv();
		d = s.getDirDroite();
		g = s.getDirGauche();
		y = s.getDirY();
		
		s.perdrePV(2, false);
		assertEquals(pv-2 , s.getPv());
		assertTrue(y>(int)s.getDirY());	
		assertTrue(g<(int)s.getDirGauche());
	
	}
}