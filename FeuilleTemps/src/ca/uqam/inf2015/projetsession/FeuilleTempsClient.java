/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

import java.io.IOException;
import java.io.FileNotFoundException;

public class FeuilleTempsClient {
    private static FeuilleTemps feuilleTemps;
    private static String jsonEntrant;
    private static String jsonSortant; 
    private static String messages;
    
    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) throws FileNotFoundException, IOException {
        jsonEntrant = args[ 0 ];
        jsonSortant = args[ 1 ];
        feuilleTemps = JSONUtilitaire.lireFichierJSON( jsonEntrant );
        messages = Regles.valider( feuilleTemps );
        JSONUtilitaire.ecrireFichierJSON( jsonSortant, messages );
    }    
}