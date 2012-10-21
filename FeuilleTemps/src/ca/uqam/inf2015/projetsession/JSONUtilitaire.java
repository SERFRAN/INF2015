/**
 *
 * @author Abdelghani Hamai
 */

package ca.uqam.inf2015.projetsession;

import net.sf.json.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.commons.io.IOUtils;

public class JSONUtilitaire {
    /**
     * Constructeur
     */
    private JSONUtilitaire() {
    }
    
    /**
     * Créé, à partir des données d'un fichier json, un objet représentant une feuille de temps.
     * @param nomFichier nom du fichier json à lire
     * @return un objet représentant une feuille de temps.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static FeuilleTemps lireFichierJSON( String nomFichier ) throws FileNotFoundException, IOException {        
        JSONObject jsonObjet;
        String jsonText;       
        
        Employe employe;
        JourTravail jourTravail;
        FeuilleTemps feuilleTemps = null;        
        
        FileInputStream inputStream;
        inputStream = new FileInputStream( nomFichier );
        jsonText = IOUtils.toString( inputStream, "UTF-8" );
        jsonObjet = (JSONObject) JSONSerializer.toJSON( jsonText );
        
        Iterator it = jsonObjet.keys();
        while ( it.hasNext() ) {
            String cle = ( String ) it.next();
            if ( cle.equals( "numero_employe" ) ) {
                employe = creerEmploye( jsonObjet.getInt( cle ) );
                feuilleTemps = creerFeuilleTemps( employe );
            } else {
                jourTravail = creerJourTravail( cle, jsonObjet.getJSONArray( cle ) );
                feuilleTemps.ajouter( jourTravail );
            }
        }        
        return feuilleTemps;
    }
    
    /**
     * Ecrit les messages de validation dans une fichier JSON.
     * @param message 
     */
    public static void ecrireFichierJSON( String fichierJson, String messages ) throws IOException {
        String jsonMessages;
        
        String delimiteur = ".";
        StringTokenizer messagesDecoupes = new StringTokenizer( messages, delimiteur );
        
        // Créé un tableau JSON de strings avec les messages de validation.
        jsonMessages = "[\n";
        int nombreChaines = messagesDecoupes.countTokens();
        if ( nombreChaines > 0 ) {
            for ( int i = 0; i < nombreChaines - 1; i++ ) {
                jsonMessages = jsonMessages + "\t" + "\"" + messagesDecoupes.nextToken() + ".\"" + "," + "\n";
            }
            jsonMessages = jsonMessages + "\t" + "\"" + messagesDecoupes.nextToken() + ".\"" + "\n";
        }
        jsonMessages = jsonMessages + "]\n";
        
        FileWriter writer = new FileWriter( fichierJson );
        writer.write( jsonMessages );
        writer.close();
    }   
    
    /**
     * Créé un objet représentant un projet à partir d'un objet JSON.
     * @param jsonObjet
     * @return 
     */
    private static Projet creerProjet( JSONObject jsonObjet ) {
        int codeProjet = jsonObjet.getInt( "projet" );
        int tempsTravail = jsonObjet.getInt( "minutes" );        
        return new Projet( codeProjet, tempsTravail );
    }
    
    /**
     * Créé un objet représentant une journée de travail d'un employé.
     * @param jour jour de travail
     * @param jsonArray un tableau JSON contenant les projets du jour.
     * @return 
     */
    private static JourTravail creerJourTravail( String jour, JSONArray jsonArray ) { 
        JourTravail jourTravail;
        int nombreProjets = jsonArray.size();
        jourTravail = new JourTravail( jour, nombreProjets );
        for ( int i = 0; i < nombreProjets; i++ ) {
            JSONObject jsonObjet = jsonArray.getJSONObject( i );
            jourTravail.ajouter( creerProjet( jsonObjet ) );
        }
        return jourTravail;
    }
    
     /**
     * Créé un objet de type représentant un employé.
     * @param numeroEmploye numéro de l'employé.
     * @return 
     */
    private static Employe creerEmploye( int numeroEmploye ) {        
        return new Employe( numeroEmploye );
    }
    
    /**
     * Créé un objet représentant une feuille de temps.
     * @param employe employé de l'entreprise
     * @return 
     */
    private static FeuilleTemps creerFeuilleTemps( Employe employe ) {        
        return new FeuilleTemps( employe );
    }
}
