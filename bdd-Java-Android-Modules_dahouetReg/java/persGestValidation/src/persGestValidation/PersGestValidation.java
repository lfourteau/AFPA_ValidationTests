/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persGestValidation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import model.Commissaire;
import model.Licencie;
import model.Personne;
import model.Proprietaire;

/**
 *
 * @author afpa
 */
public class PersGestValidation {

    public static ArrayList<Personne> pers;
    public static int actualYear = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //Création licenciés
        Licencie l1 = new Licencie(215, 25, 2017, "Fourteau", "Lucas", "sqsdqd@qsdqs.v", 1989);
        Licencie l2 = new Licencie(316, 18, 2017, "Balcon", "Yoann", "balon@Yo.com", 1987);
        Licencie l3 = new Licencie(215, 25, 2017, "Trompette", "Tanguy", "qdzd@test.fr", 1967);
        //Création propriétaire
        Proprietaire prop1 = new Proprietaire("nprop1", "pprop1", "eprop1", 1986);
        Proprietaire prop2 = new Proprietaire("nprop2", "pprop2", "eprop2", 1978);

        //Création Commissaire
        Commissaire com = new Commissaire("Bretagne", "ncom", "pcom", "ecom", 1992);

        //Création liste des personnes/propriétaires/commissaire
        pers = new ArrayList();
        pers.add(l1);
        pers.add(l2);
        pers.add(l3);
        pers.add(prop1);
        pers.add(prop2);
        pers.add(com);
        //Affichage de la liste
        for (int i = 0; i<pers.size(); i++ ) {
        System.out.println(pers.get(i));
        }

        //Test de la fonction calcul points sur licencié l1
        Calendar anneeL1 = new GregorianCalendar();
        Calendar anneeL2 = new GregorianCalendar();

        anneeL1.set(Calendar.YEAR, 2017);
        anneeL2.set(Calendar.YEAR, 2014);

        l1.calculPoints(8, anneeL1);
        l2.calculPoints(6, anneeL2);

        //Test de la foncton moyenne
        System.out.println("\n Age moyen :" + averageAge());
        medianeAge();

    }

    //Calcul moyenne d'age d'un groupe de personnes
    public static float averageAge() {
        int ageSum = 0;
        for (int i = 0; i < pers.size(); i++) {
            int birthYear = pers.get(i).getAnneeNaissance();
            ageSum += (actualYear - birthYear);
        }
        float ageAvg = ageSum / pers.size();
        return ageAvg;
    }

    public static void medianeAge() {
        //Calcul la position de la médiane
        int medPos = ((pers.size() + 1) / 2);
        //Créé et rempli un tableau contenant les ages des personnes de la liste
        int tabAge[] = new int[pers.size()];
        for (int i = 0; i < pers.size(); i++) {
            int persAge = actualYear - pers.get(i).getAnneeNaissance();
            tabAge[i] = persAge;
        }
        //Trie le tableau par ordre croissant
        Arrays.sort(tabAge);
        //Vérifie si le nombre de personnes de la liste est pair ou impair et clacul la médiane en fonction
        if (pers.size() % 2 == 0) {
            float medTemp = (tabAge[medPos] + tabAge[medPos + 1]) / 2;
            System.out.println("Médiane de l'Age : " + medTemp);
        } else {
            System.out.println("Médiane de l'Age : " + tabAge[medPos]);
        }
    }
}
