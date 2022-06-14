package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Materiaux;
import application.modele.mobs.Archer;
import application.modele.mobs.Fleche;
import application.modele.mobs.Mob;
import application.modele.mobs.Slime;
import application.modele.mobs.Squelette;
import application.vue.ImageMap;
import application.vue.InventaireVue;
import application.vue.JoueurVue;
import application.vue.MobVue;
import application.vue.PVVue;
import application.vue.TerrainVue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable{

	@FXML
    private Pane terrainPane;
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
    private javafx.scene.text.Text ferText;
    @FXML
    private javafx.scene.text.Text orText;
    @FXML
    private javafx.scene.text.Text diamantText;

	private Environnement env;
	private Timeline gameLoop;
	private MobVue mobAffichage;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//InitialiserImages
		new ImageMap();
		
		//Création Terrain
		
		env = new Environnement();
		
		TerrainVue terrainVue = new TerrainVue(env, terrainMap,this);
		terrainVue.initTerrain();

		//Indices Terrain
		int pxl = 32;
		int longueur = 120;
		int hauteur = 33;
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
		Materiaux mat = new Materiaux();
		listenDiamant(mat);
		listenOr(mat);
		listenFer(mat);

		//Mobs
		this.mobAffichage = new MobVue();
		this.env.getMobs().addListener(new MobsObsList(this));
		env.creerSlime(0, 64);
		env.creerArcher(1000, 64);
		env.creerSquelette(2000, 64);
		//Lancement Joueur
		this.bindJoueur();
		root.addEventHandler(KeyEvent.KEY_PRESSED, new ControleurTouchePresse(env));
		root.addEventHandler(KeyEvent.KEY_RELEASED, new ControleurToucheRelache(env));
		root.addEventHandler(ScrollEvent.SCROLL, new ControleurScroll(env));

		
		
		//Lancement GameLoop
		initAnimation();
		gameLoop.play();

	}

	public void listenDiamant (Materiaux mat) {
		IntegerProperty diamant = mat.diamantProperty();
		diamant.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				diamantText.setText("" + newValue);
			}
		});
	}
	public void listenOr (Materiaux mat) {
		IntegerProperty or = mat.orProperty();
		or.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				orText.setText("" + newValue);
			}
		});
	}
	public void listenFer (Materiaux mat) {
		IntegerProperty fer = mat.ferProperty();
		fer.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				ferText.setText("" + newValue);
			}
		});
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


	public void bindJoueur() {
		Joueur j = env.getJoueur();
		spriteJoueur.translateYProperty().bind(j.yProperty());
		listenJoueurProperty();
		new JoueurVue(j.xProperty(),j.yProperty(),spriteJoueur);

	}

	public void listenJoueurProperty() {
			env.getJoueur().xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((double) newValue > 960 && (double) newValue < 2880)  {
					terrainPane.setTranslateX(-(double) newValue+960);
				}
				else if ((double) newValue <= 960 ) {
					spriteJoueur.setTranslateX((double) newValue-960);
				}
				else
					spriteJoueur.setTranslateX((double) newValue-2880);
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

		KeyFrame kf = new KeyFrame(
				//FPS
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{
					
					env.unTour();
						
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
		}
		else if (m instanceof Fleche) {
			mobSprite = mobAffichage.creerFleche(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());			if (!((Fleche)m).isVersDroite()) {
				mobSprite.setScaleX(-1);
			}
		}
		else if(m instanceof Archer) {
			mobSprite = mobAffichage.creerArcher(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
		}
		
		else if(m instanceof Squelette) {
			mobSprite = mobAffichage.creerSquelette(m.getId());
			terrainPane.getChildren().add(mobSprite);
			mobSprite.translateXProperty().bind(m.xProperty());
			mobSprite.translateYProperty().bind(m.yProperty());
		}
	}

	public void supprimerSprite(Mob m) {
		if (m instanceof Slime) {
			
		}
		
	}
}

