/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancaire;

import com.DAO.EtatDAO;
import com.DAO.EtatImpl;
import com.DAO.RetraitDAO;
import com.DAO.RetraitImpl;
import com.DAO.VersementImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;


/**
 *
 * @author RAYA
 */
public class GestionBancaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EtatDAO dao = new EtatImpl();
        
        try {
            JSONArray jArray = new JSONArray(dao.etat(4));
            System.out.println(jArray);
        } catch (JSONException ex) {
            Logger.getLogger(GestionBancaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
