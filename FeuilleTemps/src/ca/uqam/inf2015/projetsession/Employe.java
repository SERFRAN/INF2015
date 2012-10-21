/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

public class Employe {
    // Le numéro d'un employé de l'administration est inférieur à 1000
    private final int TYPE_EMPLOYE = 1000;
    private int numeroEmploye;
    
    /**
     * Constructeur
     * @param numeroEmploye numéro de l'employé
     */
    public Employe( int numeroEmploye ) {
        this.numeroEmploye = numeroEmploye;
    }
    
    /**
     * Retourne true si l'employé travaille dans l'administration.
     * @return
     */
    public boolean estUnEmployeAdministration() {
        boolean employeAdministration = false;
        if ( numeroEmploye < TYPE_EMPLOYE ) {
            employeAdministration = true;
        }        
        return employeAdministration;
    }
}
