# Projet transversal F1 — Python → Java → JavaScript

Pack complet : énoncé, données, squelettes, tests, extensions et corrigés.

```
ENONCE.md                    l'énoncé étudiant (à lire en premier)
donnees/resultats.csv        l'export brut du championnat
01-python/ingestion.ipynb    maillon 1 — à compléter
02-java/src/                 maillon 2 — Classement.java à compléter
03-js/                       maillon 3 — app.js à compléter, index.html à ouvrir
secours/                     résultats de référence, en cas de blocage
extensions/E1 à E4           les extensions et leurs tests
formateur/                   corrigés, grille, générateur — À RETIRER avant distribution
```

Prérequis : Python 3 avec Jupyter, un JDK (`javac -version`), un navigateur.

*Maillon 1 — Python (01-python/ingestion.ipynb)


temps_en_secondes : convertit "1:33.996" en 93.996 (on coupe sur le :, on calcule minutes × 60 + secondes). Renvoie None si le temps est vide (abandon).
lire_resultats : lit le CSV ligne par ligne et construit une liste de dictionnaires (un par résultat). Un abandon devient la position 0.
ecrire_courses_propres: réécrit ces données nettoyées dans le format attendu par le maillon Java.

    Résultat : génère 02-java/courses_propres.csv.

*Maillon 2 — Java (02-java/src/Classement.java)

pointsPourPosition : renvoie les points selon le barème officiel (25 pour le 1er, 18 pour le 2e...), en lisant simplement le tableau BAREME.
classementPilotes : regroupe les résultats par pilote (avec une Map) et additionne points/victoires/2e places, puis trie le classement.
classementEcuries: même principe, mais regroupé par écurie.
positionMoyenne: moyenne des positions d'un pilote, abandons exclus.

    Résultat : affiche le classement dans le terminal et génère 03-js/donnees.js.

*Maillon 3 — JavaScript (03-js/app.js)

trierParPoints : trie une copie de la liste par points décroissants (copie faite avec [...liste], pour ne pas modifier la liste d'origine).
remplirTableau : construit les lignes <tr> du tableau HTML à partir de la liste triée (rang, nom, écurie, points, victoires).
marquerPodium: ajoute une classe CSS aux 3 premières lignes pour les mettre en valeur visuellement.

    Résultat : index.html affiche les classements avec le podium en surbrillance.
