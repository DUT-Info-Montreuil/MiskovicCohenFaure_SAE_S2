package application.controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.modele.Arbre;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.craft.EpeeCraft;
import application.modele.craft.OutilCraft;
import application.modele.craft.HacheCraft;
import application.modele.craft.PiocheCraft;
import application.modele.craft.materiaux.Materiaux;
import application.modele.items.utilitaires.Hache;
import application.modele.Outils;
import application.modele.mobs.Archer;
import application.modele.mobs.Boss;
import application.modele.mobs.BouleBas;
import application.modele.mobs.BouleDeFeu;
import application.modele.mobs.Fleche;
import application.modele.mobs.Mob;
import application.modele.mobs.Onde;
import application.modele.mobs.Slime;
import application.vue.ArbreVue;
import application.vue.CraftVue;
import application.modele.mobs.Squelette;
import application.modele.pnjs.Docteur;
import application.modele.pnjs.Pnj;
import application.vue.Animation;
import application.vue.AnimationMob;
import application.vue.ImageMap;
import application.vue.InventaireVue;
import application.vue.JoueurVue;
import application.vue.MobVue;
import application.vue.PVVue;
import application.vue.PnjVue;
import application.vue.TerrainVue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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

	private Environnement env;
	private Timeline gameLoop;
	private MobVue mobAffichage;
	private PnjVue pnjAffichage;
	private ArrayList <Animation> animations;
	private Label label;
	private int temps;
	private ArbreVue arbreAffichage;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//InitialiserImages
		new ImageMap();

		//Création Terrain

		env = new Environnement();
		
		this.animations=new ArrayList<Animation>();
		
		TerrainVue terrainVue = new TerrainVue(env, terrainMap);
		terrainVue.initTerrain(this.bindJoueur());

		//Indices Terrain
		int pxl = 32;
		int longueur = 240;
		int hauteur = 32;
		terrainMap.setMaxSize(longueur*pxl , hauteur*pxl);

		//PV
		PVVue pvVue = new PVVue(pointsDeVie, env.getJoueur().getPvMax());
		pvVue.initPV();
		this.listenPV(pvVue);

		//Inventaire
		InventaireVue inventaire = new InventaireVue(inventaireAff, inventaireSelect, inventaireItems, env.getJoueur().getInventaire().taille()); 
		inventaire.initInventaire();
		this.listenInventaire(inventaire);
		this.listenInventaireCase(inventaire);

		//Matériaux
		ArrayList<Materiaux> comptMat = env.getJoueur().getCompteurMateriaux();
		boisText.textProperty().bind(Bindings.convert(comptMat.get(0).matProperty()));
		ferText.textProperty().bind(Bindings.convert(comptMat.get(1).matProperty()));
		orText.textProperty().bind(Bindings.convert(comptMat.get(2).matProperty()));
		diamantText.textProperty().bind(Bindings.convert(comptMat.get(3).matProperty()));

		//Arbre
		this.arbreAffichage = new ArbreVue();
		this.env.getArbres().addListener(new ArbreObsList(this));
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			this.env.creerArbre(r.nextInt(3500-1500) + 1500, 64);


		//Pnj
		this.pnjAffichage = new PnjVue();
		this.env.getPnjs().addListener(new PnjsObsList(this));
		this.env.creerDocteur();

		//Mobs
		this.mobAffichage = new MobVue();
		this.env.getMobs().addListener(new MobsObsList(this));
		env.creerSlime(1300, 1000);
		env.creerSlime(200, 500);
		env.creerArcher(2500, 100);
		env.creerSquelette(5000, 100);
		env.creerSquelette(4500, 100);
		env.creerBoss(6500, 1000);

		//Init Craft
		CraftVue craft = new CraftVue(terrainPane, craftPane);




		//Lancement Joueur
		

		root.addEventHandler(KeyEvent.KEY_PRESSED, new ControleurTouchePresse(env, craft));
		root.addEventHandler(KeyEvent.KEY_RELEASED, new ControleurToucheRelache(env));
		root.addEventHandler(ScrollEvent.SCROLL, new ControleurScroll(env));
		
		//texte du tuto
		label=new Label();
		terrainPane.getChildren().add(label);
		label.setTranslateX(500);
		label.setTranslateY(500);

		//Lancement GameLoop
		initAnimation();
		gameLoop.play();

	}

	@FXML
	void ameliorationEpee(ActionEvent event) {
		this.env.getJoueur().getEpee().craft();
	}

	@FXML
	void ameliorationHache(ActionEvent event) {
		this.env.getJoueur().getHache().craft();
	}

	@FXML
	void ameliorationPioche(ActionEvent event) {
		this.env.getJoueur().getPioche().craft();
	}

	public void listenPV(PVVue pvVue) {
		IntegerProperty pv = env.getJoueur().pvProperty();

		pv.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				pvVue.changerPV((int) newValue);
			}
		});
	}

	public void listenInventaire(InventaireVue inv) {
		IntegerProperty curseur = env.getJoueur().getInventaire().indexProperty();
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
		IntegerProperty curseurCase = env.getJoueur().getInventaire().indexCaseProperty();
		curseurCase.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String idItem = env.getJoueur().getInventaire().idItemEnMain();
				int curseur = env.getJoueur().getInventaire().getIndexProperty();
				inv.changerImage(curseur, idItem);
			}
		});
	}


	public JoueurVue bindJoueur() {
		Joueur j = env.getJoueur();
		JoueurVue a=new JoueurVue(j.getDirDroiteProperty(),j.getDirGaucheProperty(),j.xProperty(),j.yProperty(),j.pvProperty(),spriteJoueur,this.terrainPane);
		spriteJoueur.translateYProperty().bind(j.yProperty());
		listenJoueurXProperty();
		this.animations.add(a);
		return a;

	}

	public void listenJoueurXProperty() {
		env.getJoueur().xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((double) newValue > 960 && (double) newValue < 6720)  {
					terrainPane.setTranslateX(-(double) newValue+960);
				}
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

	private void initAnimation() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		temps=0;

		KeyFrame kf = new KeyFrame(
				//FPS
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{

					for (Animation a:this.animations) {
						a.action();
					}
					env.unTour();
					String texte = "Bonjour aventurier! Bienvenue dans la fameuse ville de nowhere.\n"
							+ "Nous sommes oppressés par un mage qui invoque des créatures maléfiques.\n"
							+ "Pourriez vous vaincre ce tyran pour nous?\n"
							+ "Je vous aiderai dans votre quête en vous soignant,\npour cela il vous suffit d'appuyer sur moi avec le clique droit.";
					if (temps<=texte.length()*5) {
						label.setText(texte.substring(0, temps/5));
						temps++;
					}
				})
				);
		gameLoop.getKeyFrames().add(kf);
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
			this.animations.add(new AnimationMob(m.xProperty(),m.yProperty(),m.pvProperty(), mobSprite,this.terrainPane));
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
					if (Outils.verifRange(env.getJoueur().getX(), env.getJoueur().getY(), Outils.coordToTile(p.getX(),p.getY()))) {
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
				if (Outils.verifRange(env.getJoueur().getX(), env.getJoueur().getY(), Outils.coordToTile(a.getX(),a.getY()+50))) {
				if (env.getJoueur().getInventaire().itemEnMain() instanceof Hache) {
						 a.perdrePV(((Hache) env.getJoueur().getInventaire().itemEnMain()).getDegats());
						 arbreSprite.setOpacity((double) a.getPv()/10);
				}
				}
			}
		});
		terrainPane.getChildren().add(arbreSprite);
		arbreSprite.translateXProperty().bind(a.xProperty());
		arbreSprite.translateYProperty().bind(a.yProperty());
	}

	public void supprimerSprite(Mob m) {
		if (m instanceof Slime) {

		}
	}
}

