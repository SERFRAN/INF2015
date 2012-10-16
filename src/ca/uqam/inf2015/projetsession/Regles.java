/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

public class Regles {    
    private static final int MIN_HEURES_BUREAU_NOR = 38;
    private static final int MIN_HEURES_BUREAU_ADM = 36;
    
    private static final int MAX_HEURES_BUREAU = 43;
    private static final int MAX_HEURES_TELETRAVAIL_ADM = 10;
    
    private static final int MIN_HEURES_BUREAU_NOR_JO = 6;
    private static final int MIN_HEURES_BUREAU_ADM_JO = 4;
    
    /**
     * Constructeur
     */
    private Regles() {        
    }
    
    /**
     * Valide une feuille de temps d'un employé.
     * @param feuilleTemps feuille de temps
     * @return messages de validation
     */
    public static String valider( FeuilleTemps feuilleTemps ) {
        String messages = "";        
        
        if ( ! aTravailleMinHeuresBureau( feuilleTemps ) ) {
            messages = messages + "L'employé n'a pas travaillé le nombre d'heures minimal au bureau.";
        }        
        if ( aDepasseMaxHeuresBureau( feuilleTemps ) ) {
            messages = messages + "L'employé a dépassé le nombre d'heures maximal au bureau.";
        }
        if ( aDepasseMaxHeuresTeletravail( feuilleTemps ) ) {
            messages = messages + "L'employé a dépassé le nombre d'heures maximal de télétravail.";
        }       
        if ( ! aTravailleMinHeuresBureauJOuvrables( feuilleTemps ) ) {
            messages = messages + "L'employé n'a pas travaillé le nombre d'heures minimal au bureau pour les jours ouvrables.";
        }        
        return messages;
    }
    
    /**
     * Retourne vrai si l'employé a travaillé le nombre minimal d'heures au bureau.
     * @param feuilleTemps
     * @return 
     */
    private static boolean aTravailleMinHeuresBureau( FeuilleTemps feuilleTemps ) {
        boolean regle = true;
        int tempsTravail = feuilleTemps.obtenirTempsBureau();
        Employe employe = feuilleTemps.obtenirEmploye();
        if ( employe.estUnEmployeAdministration() ) {
            if ( tempsTravail < MIN_HEURES_BUREAU_ADM * 60 ) {
                regle = false;
            }
        } else {
            if ( tempsTravail < MIN_HEURES_BUREAU_NOR * 60 ) {
                regle = false;
            }
        }
        return regle;
    }
    
    /**
     * Retourne vrai si l'employé a dépassé le nombre maximal d'heures au bureau.
     * @param feuilleTemps
     * @return 
     */
    private static boolean aDepasseMaxHeuresBureau( FeuilleTemps feuilleTemps ) {
        boolean regle = true;
        int tempsTravail = feuilleTemps.obtenirTempsBureau();
        if ( tempsTravail <= MAX_HEURES_BUREAU * 60 ) {
            regle = false;
        }        
        return regle;
    }
    
    /**
     * Retourne vrai si l'employé a dépassé le nombre maximal d'heures par télétravail.
     * @param feuilleTemps
     * @return 
     */
    private static boolean aDepasseMaxHeuresTeletravail( FeuilleTemps feuilleTemps ) {
        boolean regle = true;
        int tempsTravail = feuilleTemps.obtenirTempsTeletravail();
        Employe employe = feuilleTemps.obtenirEmploye();
        if ( employe.estUnEmployeAdministration() ) {
            if ( tempsTravail <= MAX_HEURES_TELETRAVAIL_ADM * 60 ) {
                regle = false;
            }
        } else {
            regle = false;
        }
        return regle;
    }
    
    /**
     * Retourne vrai si l'employé a travaillé le minimum d'heures au bureau aux jours ouvrables.
     * @param feuilleTemps
     * @return 
     */
    private static boolean aTravailleMinHeuresBureauJOuvrables( FeuilleTemps feuilleTemps ) {
        boolean regle = true;
        int tempsTravail = feuilleTemps.obtenirTempsBureauJOuvrables();
        Employe employe = feuilleTemps.obtenirEmploye();
        if ( employe.estUnEmployeAdministration() ) {
            if ( tempsTravail < MIN_HEURES_BUREAU_ADM_JO * 60 ) {
                regle = false;
            }
        } else {
            if ( tempsTravail < MIN_HEURES_BUREAU_NOR_JO * 60 ) {
                regle = false;
            }
        }
        return regle;
    }
}
