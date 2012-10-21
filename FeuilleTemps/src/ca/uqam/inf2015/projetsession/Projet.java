/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

public class Projet {
    private final int TYPE_PROJET = 900;
    private int codeProjet;
    private int tempsTravail;
    
    /**
     * Constructeur
     * @param codeProjet code du projet
     * @param tempsTravail minutes travaillées
     */
    public Projet( int codeProjet, int tempsTravail ) { 
        this.codeProjet = codeProjet;
        this.tempsTravail = tempsTravail;
    }
    
    /**
     * Retourne true si le projet est un projet télétravail.
     * @return 
     */
    public boolean estUnProjetTeletravail() {
        boolean projetTeletravail = false;         
        if ( codeProjet > TYPE_PROJET ) {
            projetTeletravail = true;
        }        
        return projetTeletravail;
    }
    
    /**
     * Retourne le temps travaillé en minutes.
     * @return minutes travaillées
     */
    public int obtenirTempsTravail() {
        return tempsTravail;
    }
}
