/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

import java.util.ArrayList;
import java.util.Iterator;

public class JourTravail {
    private String jour;
    private ArrayList< Projet > projets;
    private int nombreProjets;
    
    /**
     * Constructeur
     * @param idJour jour de travail
     * @param nombreProjets nombre de projets du jour
     */
    public JourTravail( String jour, int nombreProjets ) {
        this.jour = jour;        
        this.nombreProjets = nombreProjets;
        projets = new ArrayList< Projet >( nombreProjets );
    }
    
    /**
     * Ajoute un projet au jour de travail. 
     * @param projet projet à ajouter
     */
    public void ajouter( Projet projet ) { 
        projets.add( projet );        
    }
    
    /**
     * Calcule le temps travaillé au bureau en minutes.
     * @return 
     */
    public int obtenirTempsBureau() {
        int tempsBureau = 0;
        Iterator< Projet > it = projets.iterator();
        while ( it.hasNext() ) {
            Projet projet = it.next();
            if ( ! projet.estUnProjetTeletravail() ) {
                tempsBureau = tempsBureau + projet.obtenirTempsTravail();
            }
        }
        return tempsBureau;        
    }
    
    /**
     * Calcule te temps télétravaillé en minutes.
     * @return 
     */
    public int obtenirTempsTeletravail() {
        int tempsTeletravail = 0;
        Iterator< Projet > it = projets.iterator();
        while ( it.hasNext() ) {            
            Projet projet = it.next();
            if ( projet.estUnProjetTeletravail() ) {
                tempsTeletravail = tempsTeletravail + projet.obtenirTempsTravail();
            }
        }       
        return tempsTeletravail;
    }
    
     /**
     * Retourne vrai si le jour est un jour ouvrable.
     * @return
     */
    public boolean estUnJourOuvrable() {
        boolean jourOuvrable = true;
        if ( jour.equals( "weekend1" ) || jour.equals( "weekend2" ) ) {
            jourOuvrable = false;
        }        
        return jourOuvrable;
    }    
}
