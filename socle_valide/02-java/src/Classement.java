/* =========================================================================
   MAILLON 2 — JAVA : le moteur de calcul
   Complétez les quatre méthodes. Les classes Ligne, Resultat et Chargeur
   sont fournies : ne les modifiez pas.
       javac -encoding UTF-8 -d out src/*.java
       java -Dstdout.encoding=UTF-8 -cp out Tests     (les tests)
       java -Dstdout.encoding=UTF-8 -cp out Main      (la production)
   ========================================================================= */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Classement {

    /** Barème officiel des dix premiers. FOURNI — NE PAS MODIFIER. */
    public static final int[] BAREME = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    // 1. pointsPourPosition(position) : points marqués pour cette position.
    public static int pointsPourPosition(int position) {
        if (position < 1 || position > 10) {
            return 0;
        }
        return BAREME[position - 1];
    }

    // 2. classementPilotes(lignes) : un Resultat par pilote.
    public static List<Resultat> classementPilotes(List<Ligne> lignes) {
        Map<String, Resultat> parPilote = new LinkedHashMap<>();

        for (Ligne ligne : lignes) {
            Resultat r = parPilote.get(ligne.pilote());
            if (r == null) {
                r = new Resultat(ligne.pilote(), ligne.ecurie());
                parPilote.put(ligne.pilote(), r);
            }
            r.points += pointsPourPosition(ligne.position());
            if (ligne.position() == 1) {
                r.victoires++;
            }
            if (ligne.position() == 2) {
                r.deuxiemes++;
            }
        }

        List<Resultat> classement = new ArrayList<>(parPilote.values());
        classement.sort((a, b) -> {
            if (a.points != b.points) {
                return b.points - a.points;
            }
            if (a.victoires != b.victoires) {
                return b.victoires - a.victoires;
            }
            if (a.deuxiemes != b.deuxiemes) {
                return b.deuxiemes - a.deuxiemes;
            }
            return a.nom.compareTo(b.nom);
        });

        return classement;
    }

    // 3. classementEcuries(pilotes) : additionne les points de ses pilotes.
    public static List<Resultat> classementEcuries(List<Resultat> pilotes) {
        Map<String, Resultat> parEcurie = new LinkedHashMap<>();

        for (Resultat p : pilotes) {
            Resultat e = parEcurie.get(p.ecurie);
            if (e == null) {
                e = new Resultat(p.ecurie, "");
                parEcurie.put(p.ecurie, e);
            }
            e.points += p.points;
            e.victoires += p.victoires;
            e.deuxiemes += p.deuxiemes;
        }

        List<Resultat> classement = new ArrayList<>(parEcurie.values());
        classement.sort((a, b) -> {
            if (a.points != b.points) {
                return b.points - a.points;
            }
            if (a.victoires != b.victoires) {
                return b.victoires - a.victoires;
            }
            if (a.deuxiemes != b.deuxiemes) {
                return b.deuxiemes - a.deuxiemes;
            }
            return a.nom.compareTo(b.nom);
        });

        return classement;
    }

    // 4. positionMoyenne(lignes, pilote) : moyenne des positions, abandons exclus.
    public static double positionMoyenne(List<Ligne> lignes, String pilote) {
        int somme = 0;
        int nombre = 0;

        for (Ligne ligne : lignes) {
            if (ligne.pilote().equals(pilote) && ligne.position() != 0) {
                somme += ligne.position();
                nombre++;
            }
        }

        if (nombre == 0) {
            return 0;
        }

        double moyenne = (double) somme / nombre;
        return Math.round(moyenne * 100.0) / 100.0;
    }
}