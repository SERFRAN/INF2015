/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

import java.util.ArrayList;
import java.util.Iterator;

public class FeuilleTemps {
    private Employe employe;
    private ArrayList< JourTravail > joursTravail;
    
    /**
     * Constructeur
     */
    public FeuilleTemps( Employe employe ) {
        this.employe = employe;        
        joursTravail = new ArrayList< JourTravail >(7);
    }
    
    /**
     * Ajoute un jour de travail à la feuille de travail. 
     * @param jourTravail 
     */
    public void ajouter( JourTravail jourTravail ) {
        joursTravail.add( jourTravail );
    }
    
    /**
     * Retourne le temps travaillé au bureau pendant la semaine en minutes.
     * @return 
     */
    public int obtenirTempsBureau() {
        int tempsBureau = 0;
        Iterator< JourTravail > it = joursTravail.iterator();
        while ( it.hasNext() ) {
            JourTravail jourTravail = it.next();
            tempsBureau = tempsBureau + jourTravail.obtenirTempsBureau();
        }
        return tempsBureau;
    }
    
    /**
     * Retourne le temps télétravaillé pendant la semaine en minutes.
     * @return 
     */
    public int obtenirTempsTeletravail() {
        int tempsTeletravail = 0;
        Iterator< JourTravail > it = joursTravail.iterator();
        while ( it.hasNext() ) {
            JourTravail jourTravail = it.next();        
            tempsTeletravail = tempsTeletravail + jourTravail.obtenirTempsTeletravail();
        }
        return tempsTeletravail;
    }
    
    /**
     * Retourne le temps travaillé au bureau aux jours ouvrables en minutes.
     * @return 
     */
    public int obtenirTempsBureauJOuvrables() {
        int tempsBureauJOuvrables = 0;
        Iterator< JourTravail > it = joursTravail.iterator();
        while ( it.hasNext() ) {
            JourTravail jourTravail = it.next();  
            if ( jourTravail.estUnJourOuvrable() ) {
                tempsBureauJOuvrables = tempsBureauJOuvrables + jourTravail.obtenirTempsBureau();
            }
        }
        return tempsBureauJOuvrables;
    } 
    
    /**
     * Valide si le nombres d'heures minimales journalieres est respectes.
     * Retourne vrai si le minumum est respecte pour tous les jours
     * Retroune faux si un seul des jours n'est pas respecte
     * @return  
     */
    public boolean ValiderTempsBureauJOuvrables(int heureMinale) {
        boolean jourValide = true;
        Iterator< JourTravail > it = joursTravail.iterator();
        while ( it.hasNext() && jourValide == true ) {
            JourTravail jourTravail = it.next();  
            if ( jourTravail.estUnJourOuvrable() ) {
                if (jourTravail.obtenirTempsBureau() < heureMinale)
                {
                  jourValide = false;
                }
            }
        }
        return jourValide;
    } 
    
    /**
     * Retoune l'employé associé à la feuille de temps
     * @return 
     */
    public Employe obtenirEmploye() {
        return employe;
    }
}
