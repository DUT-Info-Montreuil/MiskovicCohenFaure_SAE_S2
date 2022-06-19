package application.controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.modele.Arbre;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.craft.materiaux.Materiaux;
import application.modele.items.utilitaires.Hache;
import application.modele.Outils;
import application.modele.mobs.Archer;
import application.modele.mobs.Fleche;
import application.modele.mobs.Slime;
import application.vue.ArbreVue;
import application.vue.CraftVue;
import application.modele.mobs.Squelette;
import application.modele.mobs.boss.Boss;
import application.modele.mobs.boss.BouleBas;
import application.modele.mobs.boss.BouleDeFeu;
import application.modele.mobs.boss.Mob;
import application.modele.mobs.boss.Onde;
import application.modele.pnjs.Docteur;
import application.modele.pnjs.Pnj;
import application.vue.Animation;
import application.vue.AnimationBoss;
import application.vue.AnimationMob;
import application.vue.ImageMap;
import application.vue.InventaireVue;
import application.vue.JoueurVue;
import application.vue.MobVue;
import application.vue.PVVue;
import application.vue.PnjVue;
import application.vue.TerrainVue;
import application.vue.TexteTutoVue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controleur implements Initializable{

	@FXML
	private Pane terrainPane;
	@FXML
	private Pane craftPane;
	@FXML
	private TilePane terrainMap;
	@FXML
	private ImageView spriteJoueur;
	@FXML
	private ImageView spriteSlime;
	@FXML
	private BorderPane root;
	@FXML
	private HBox pointsDeVie;
	@FXML
	private HBox inventaireAff;
	@FXML
	private HBox inventaireSelect;
	@FXML
	private HBox inventaireItems;
	@FXML
	private Text boisText;
	@FXML
	private Text ferText;
	@FXML
	private Text orText;
	@FXML
	private Text diamantText;
	@FXML
	private Button bouttonEpee;
	@FXML
	private Button bouttonPioche;
	@FXML
	private Button bouttonHache;
	@FXML
	private TextArea recetteHache;
	@FXML
	private TextArea recetteEpee;
	@FXML
	private TextArea recettePioche;

	private Environnement env;
	private Joueur joueur;
	private Timeline gameLoop;
	private MobVue mobAffichage;
	private PnjVue pnjAffichage;
	private ArrayList <Animation> animations;
	private Label texteTuto;
	private int temps;
	private ArbreVue arbreAffichage;
	private CraftVue craft;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Initialisation Image Map
		new ImageMap();

		//Initialisation Environnement
		this.env = new Environnement();
		this.joueur = env.getJoueur();

		//Initialisation Animation
		this.animations = new ArrayList<Animation>();

		//Initialisation Terrain
		TerrainVue terrainVue = new TerrainVue(env, terrainMap);
		terrainVue.initTerrain();

		int pxl = 32;
		int longueur = 240;
		int hauteur = 32;
		terrainMap.setMaxSize(longueur*pxl , hauteur*pxl);

		//Initialisation PV
		PVVue pvVue = new PVVue(pointsDeVie, this.joueur.getPvMax());
		pvVue.initPV();
		this.listenPV(pvVue, this.joueur);

		//Initialisation Inventaire
		InventaireVue inventaire = new InventaireVue(inventaireAff, inventaireSelect, inventaireItems, this.joueur.getInventaire().taille()); 
		inventaire.initInventaire();
		this.listenInventaire(inventaire);
		this.listenInventaireCase(inventaire);

		//Initialisation Matériaux
		ArrayList<Materiaux> comptMat = this.joueur.getCompteurMateriaux();

		boisText.textProperty().bind(Bindings.convert(comptMat.get(0).matProperty()));
		ferText.textProperty().bind(Bindings.convert(comptMat.get(1).matProperty()));
		orText.textProperty().bind(Bindings.convert(comptMat.get(2).matProperty()));
		diamantText.textProperty().bind(Bindings.convert(comptMat.get(3).matProperty()));

		//Initialisation Arbre
		this.arbreAffichage = new ArbreVue();
		this.env.getArbres().addListener(new ArbreObsList(this));

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			this.env.creerArbre(r.nextInt(3500-1500) + 1500, 64);
		}

		//Initialisation PNJ
		this.pnjAffichage = new PnjVue();
		this.env.getPnjs().addListener(new PnjsObsList(this));
		this.env.creerDocteur(600, 500);
		this.env.creerDocteur(5700, 500);

		//Intialisation Mobs
		this.mobAffichage = new MobVue();
		this.env.getMobs().addListener(new MobsObsList(this));
		this.env.creerSlime(1300, 1000);
		this.env.creerSlime(200, 500);
		this.env.creerArcher(2500, 100);
		this.env.creerSquelette(5000, 100);
		this.env.creerSquelette(4500, 100);
		this.env.creerBoss(6500, 750);

		//Initialisation Craft
		this.craft = new CraftVue(terrainPane, craftPane);

		//Lancement Joueur
		root.addEventHandler(KeyEvent.KEY_PRESSED, new ControleurTouchePresse(env, craft));
		root.addEventHandler(KeyEvent.KEY_RELEASED, new ControleurToucheRelache(env));
		root.addEventHandler(ScrollEvent.SCROLL, new ControleurScroll(env));
		terrainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurSourisCliqueAttaque(env.getJoueur(),bindJoueur()));

		//Initialisation Texte Tuto
		TexteTutoVue tutoTextVue = new TexteTutoVue(terrainPane);
		this.texteTuto = tutoTextVue.getTxt();

		//Lancement GameLoop
		initAnimation();
		gameLoop.play();

	}

	// LISTENERS
	public void listenPV(PVVue pvVue, Joueur j) {
		IntegerProperty pv = this.joueur.pvProperty();
		pv.addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//Permet d'actualiser barre de vie en fonction du modèle
				pvVue.changerPV((int) newValue);
				//Gestion du respawn, quand PV<=0 on apelle méthode respawn
				if ((int) newValue <= 0) {
					respawnJoueur(j);
				}
			}
		});
	}
	public void respawnJoueur(Joueur j) {
		j.setPv(5);
		j.setX(960);
		j.setY(450);
		this.terrainPane.setTranslateX(0);
	}
	
	public void listenInventaire(InventaireVue inv) {
		IntegerProperty curseur = this.joueur.getInventaire().indexProperty();
		inv.positionnerCurseur(curseur.get());
		curseur.addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				inv.enleverCurseur((int) oldValue);
				inv.positionnerCurseur((int) newValue);
			}
		});
	}
	public void listenInventaireCase(InventaireVue inv) {
		IntegerProperty curseurCase = this.joueur.getInventaire().indexCaseProperty();
		curseurCase.addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String idItem = joueur.getInventaire().idItemEnMain();
				int curseur = joueur.getInventaire().getIndexProperty();
				inv.changerImage(curseur, idItem);
			}
		});
	}

	public JoueurVue bindJoueur() {
		//Animation Joueur
		JoueurVue animJoueur = new JoueurVue (this.joueur.getDirDroiteProperty(),this.joueur.getDirGaucheProperty(),this.joueur.xProperty(),this.joueur.yProperty(),this.joueur.pvProperty(),spriteJoueur,this.terrainPane);
		//Translation coordonnée modèle vers sprite
		spriteJoueur.translateYProperty().bind(this.joueur.yProperty());
		listenJoueurXProperty();
		
		this.animations.add(animJoueur);
		return animJoueur;

	}
	public void listenJoueurXProperty() {
		this.joueur.xProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//Déplacement du terrain
				if ((double) newValue > 960 && (double) newValue < 6720)  {
					terrainPane.setTranslateX(-(double) newValue+960);
				}
				//Gestion aux bords du terrain, permet de bloquer et de ne pas afficher du vide
				else if ((double) newValue <= 960 ) {
					spriteJoueur.setTranslateX((double) newValue-960);
				}
				else
					spriteJoueur.setTranslateX((double) newValue-6720);
			}
		});
	}


	//GESTION SPRITE
	public void enleverSprite (String id) {
		terrainPane.lookup("#" + id).setVisible(false);
		terrainPane.getChildren().remove(terrainPane.lookup("#" + id));
	}

	public void ajouterJoueur (ImageView sprite) {
		this.terrainMap.getChildren().add(sprite);
	}

	public void creerSpriteMob(Mob m) {
		ImageView mobSprite = null;
		if (m instanceof Slime) {
			mobSprite = mobAffichage.creerSlime(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
			this.animations.add(new AnimationMob(m.xProperty(),m.yProperty(),m.pvProperty(), mobSprite,this.terrainPane));
		}
		else if (m instanceof BouleDeFeu) {
			mobSprite = mobAffichage.creerBouleDeFeu(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());		
			this.animations.add(new Animation(m.xProperty(),m.yProperty(), mobSprite));
		}
		else if (m instanceof BouleBas) {
			mobSprite = mobAffichage.creerBouleDeFeu(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());		
			mobSprite.setRotate(90);
		}
		else if(m instanceof Archer) {
			mobSprite = mobAffichage.creerArcher(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
			this.animations.add(new AnimationMob(m.xProperty(),m.yProperty(),m.pvProperty(), mobSprite,this.terrainPane));
		}
		else if(m instanceof Squelette) {
			mobSprite = mobAffichage.creerSquelette(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
			this.animations.add(new AnimationMob(m.xProperty(),m.yProperty(),m.pvProperty(), mobSprite,this.terrainPane));
		}
		else if(m instanceof Boss) {
			mobSprite = mobAffichage.creerBoss(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
			this.animations.add(new AnimationBoss(m.xProperty(),m.yProperty(),m.pvProperty(), mobSprite,this.terrainPane));
		}
		else if (m instanceof Onde) {
			mobSprite = mobAffichage.creerOnde(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());		
		}
		else if (m instanceof Fleche) {
			mobSprite = mobAffichage.creerFleche(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());		
			this.animations.add(new Animation(m.xProperty(),m.yProperty(), mobSprite));
		}

	}

	public void creerSpritePnj(Pnj p) {
		ImageView pnjSprite = null;
		if (p instanceof Docteur) {
			pnjSprite = this.pnjAffichage.creerDocteur(p.getId());
			pnjSprite.setOnMouseClicked(event ->
			{
				if (event.getButton() == MouseButton.SECONDARY)
				{
					if (Outils.verifRange(this.joueur.getX(), this.joueur.getY(), Outils.coordToTile(p.getX(),p.getY()))) {
						((Docteur) p).soigne();
					}
				}
			});

			terrainPane.getChildren().add(pnjSprite);
			pnjSprite.translateXProperty().bind(p.xProperty());
			pnjSprite.translateYProperty().bind(p.yProperty());

		} 
	}

	public void creerSpriteArbre(Arbre a) {
		final ImageView arbreSprite = this.arbreAffichage.creerArbre(a.getId());
		arbreSprite.setOnMouseClicked(event ->
		{
			if (event.getButton() == MouseButton.PRIMARY)
			{
				if (Outils.verifRange(this.joueur.getX(), this.joueur.getY(), Outils.coordToTile(a.getX(),a.getY()+50))) {
					if (this.joueur.getInventaire().itemEnMain() instanceof Hache) {
						a.perdrePV(((Hache) this.joueur.getInventaire().itemEnMain()).getDegats());
						arbreSprite.setOpacity((double) a.getPv()/10);
					}
				}
			}
		});
		terrainPane.getChildren().add(arbreSprite);
		arbreSprite.translateXProperty().bind(a.xProperty());
		arbreSprite.translateYProperty().bind(a.yProperty());
	}

	//GAME LOOP
	private void initAnimation() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		temps=0;

		KeyFrame kf = new KeyFrame(
				//FPS
				Duration.seconds(0.017), 
				(ev ->{
					for (Animation a: this.animations) {
						a.action();
					}
					env.unTour();
					String texte = "Bonjour aventurier! Bienvenue dans la fameuse vallée de Nowhere.\n"
							+ "Nous sommes oppressés par un mage qui invoque des créatures maléfiques.\n"
							+ "Pourriez vous vaincre ce tyran pour nous?\n"
							+ "Je vous aiderai dans votre quête en vous soignant,\npour cela il vous suffit d'appuyer sur moi avec le clique droit.";
					if (temps<=texte.length()*5) {
						texteTuto.setText(texte.substring(0, temps/5));
						temps++;
					}
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
	
	//CRAFT
	
	@FXML
	void ameliorationEpee(ActionEvent event) {
		int indMax = this.joueur.getEpee().getOutilCase().getIndexMax();
		if (this.joueur.getEpee().craft()) {
			this.craft.ameliorerTextEpee(recetteEpee, indMax);
			if (indMax == 3) {
				this.craft.cacherBoutton(bouttonEpee);
				this.craft.cacherTextArea(recetteEpee);
			}
		}
	}

	@FXML
	void ameliorationHache(ActionEvent event) {
		int indMax = this.joueur.getHache().getOutilCase().getIndexMax();
		if (this.joueur.getHache().craft()) {
			this.craft.ameliorerTextPiocheHache(recetteHache, indMax);
			if (indMax == 3) {
				this.craft.cacherBoutton(bouttonHache);
				this.craft.cacherTextArea(recetteHache);
			}
		}
	}

	@FXML
	void ameliorationPioche(ActionEvent event) {
		int indMax = this.joueur.getPioche().getOutilCase().getIndexMax();
		if (this.joueur.getPioche().craft()) {
			this.craft.ameliorerTextPiocheHache(recettePioche, indMax);
			if (indMax == 3) {
				this.craft.cacherBoutton(bouttonPioche);
				this.craft.cacherTextArea(recettePioche);
			}
		}
	}

}

