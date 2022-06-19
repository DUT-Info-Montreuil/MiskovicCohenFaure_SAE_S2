# MiskovicCohenFaure_SAE_S2

# MiskovicCohenFaure_SAE_S2

Groupe: MISKOVIC Théo, COHEN Hugo et FAURE Grégoire

# SaE_Dev_S2
Terraria-Like

◦ L’univers du jeu, la quête.
    Nous sommes un voyageur qui arrive dans une vallée. Dénué de tout item, un étrange personnage nous demande de sauver leur chef détenu par un mage dans un donjon. Le jeu sera fini une fois le mage battu.

La map est constituée de deux montagnes aux extrémités infranchissables et incassables. 
le donjon est une zone spéciale.  On ne peut ni placer de blocs ni en casser.
Les limites verticales ne sont pas visibles, il ya juste un moment ou l’écran ne scroll plus.

◦ Les ressources que l’on peut trouver et leur rôle.

Bois : se situe dans les forêts sous forme d’arbre. Ces derniers sont “en fond” c'est-à-dire que l’on peut passer au travers. Casser le bloc à la base de l’arbre permet de casser l’arbre entier et le bois ira directement dans l’inventaire. Le bois n’est pas un bloc plaçable.
    (Se casse avec la main ou une hache. Plus la hache est d’un minerais rare, plus la casse est rapide)

Terre et Herbe : Elle représente les premières couches au sol avant la pierre. On peut la récupérer et la placer.
    Se casse avec une pioche.

Pierre : Elle se situe plus profondément dans le sol que la terre. Elle est cassable et plaçable. Elle constitue à la fois un objet plaçable et une ressource. 
    Se casse avec une pioche.

Minerais : (Fer, Or, Diamant) : Non plaçable. Permet la construction d’outils, d’armes et armures plus puissants.
(pour casser chaque minerai il faut un outil du minerai d’avant.) 

◦ Les objets que l’on peut construire, ce qu’ils permettent, avec quoi ils se construisent et leurs conditions d’utilisations.

Épée : Seul moyen d’attaquer les ennemis. Attaquer les ennemis les fait reculer. Nécessite un bout de bois et deux de minerais.
Pioche : Sert à casser la pierre et les minerais. Nécessite un bout de bois et trois bouts de minerais.
Hache : Sert à couper les arbres de plus en plus rapidement. Nécessite un bout de bois et trois bouts de minerais.
Arc : Equipé par défaut.

L’efficacité de l’épée, de la pioche, de la hache ou de l’armure augmente en fonction du matériau utilisé (pierre < fer < or < diamant).



◦ Quelques ennemis avec leur mode de déplacement et d’attaque ou quelques PNJ avec leur rôle et leur mode de déplacement.

Ennemis :
    Chaque ennemi a chacun 2 phases, une passive, lorsqu’il surveille son environnement, une agressive, lorsqu’il attaque le joueur.
Chaque ennemi est équipé d’une barre de vie.

Slime : 
        Emplacement : Plaine
Mode de déplacement : Au sol.
Déplacements passifs : Aléatoire, saute vers la droite ou vers la gauche, en marquant une pause entre chaque saut.
Déplacements agressifs : (lorsqu’il détecte le joueur) : Se dirige vers le joueur en sautant.
Attaque (au corps à corps) : Entre en contact avec le joueur et lui inflige des dégâts.

Squelette Archer : 
    Emplacement : Foret.
    Mode de déplacement : Au sol.
Déplacements passifs : Aléatoire, marche vers la droite ou vers la gauche.
Déplacements agressifs (lorsqu’il détecte le joueur) : Se dirige vers le joueur et s’arrête lorsqu’il est à portée d’attaque.
Attaque (à distance) : Tire des flèches à l’aide d’un arc.

Squelette Guerrier : 
    Emplacement : Terrain rocheux.
Mode de déplacement : Au sol.
Déplacements passifs : Aléatoire, marche vers la droite ou vers la gauche.
Déplacements agressifs (lorsqu’il détecte le joueur) : Se dirige vers le joueur et s’arrête lorsqu’il est à portée une certaine portée.
Attaque (au corps à corps) : Donne des coups d’épée.

Mage (Boss) :
    Emplacement : Dernière salle du donjon
    Mode de déplacement : volant et au sol.
    Déplacement : se déplace de droite à gauche au sol ou dans les airs.
    Attaques : Possède plusieurs phases et compétences : tire


Allié :

Médecin (sous-classe du villageois) :  Peut régénérer la vie du joueur.


Contrôles :

Q et D -> déplacements gauche et droite
Espace -> Sauter
Clique Gauche -> Attaquer/Casser/Couper
Clique Droit -> Interagir avec le médecin
DIGIT1 [touche : &] -> Positionner le curseur au niveau de la épée dans l’inventaire

DIGIT2 [touche : é] -> Positionner le curseur au niveau de la pioche dans l’inventaire

DIGIT3 [touche : “]- >Positionner le curseur au niveau de la hache dans l’inventaire

DIGIT4 [touche : ‘] -> Positionner le curseur au niveau de l’arc dans l’inventaire

DIGIT5 [touche : (] -> Positionner le curseur au niveau des blocs dans l’inventaire

Molette Scroll vers le haut/bas -> Changer d’item dans la case où on se situe (ex : Passer de la pioche en pierre à la pioche en fer/Changer de bloc)
Scroller vers le haut vous donnera toujours le meilleur outils que vous possédez.

E -> Ouvrir l’interface de craft

Vie: le personnage à 5 cœurs qu’il peut perdre face aux différents ennemis du jeu.

Récupération d’item: lorsque l’on casse un bloc ou construit un item, celui se place directement dans notre inventaire, il n’y a donc pas de gestion d’inventaire à faire.

 Gestion des minerais: les minerais sont affichés à droite de l’écran, accompagné de leur nombre, si on en a 0 rien ne s’affiche.
