/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Retrait;
import com.Model.Versement;
import java.util.Iterator;

/**
 *
 * @author RAYA
 */
public class EtatImpl implements EtatDAO {

    @Override
    public String etat(int id) {
        System.out.println(id);
        String reponses = "";
        VersementDAO dao_versemnt = new VersementImpl();
        RetraitDAO dao_retrait = new RetraitImpl();
        

        reponses = "{";
        reponses = reponses + "\"versement\": [";
        for(Versement versement : dao_versemnt.getsearch(id)){
            reponses = reponses + "{\"id\":\"";

                reponses = reponses + versement.getIdVersement();

                reponses = reponses + "\",\"montant_versement\":\"";

                reponses = reponses + versement.getMontant();
                
                reponses = reponses + "\",\"montant_retrait\":\"";

                reponses = reponses + 0;

                reponses = reponses + "\",\"date_versement\":\"";

                reponses = reponses + versement.getDate()+ "\"},";
        }
       
        if (reponses.charAt(reponses.length() - 1) == ',') {
            reponses = reponses.substring(0, reponses.length() - 1);
        }
        reponses = reponses + "],";
        reponses = reponses + " \"retrait\": [\"";
        for(Retrait retrait : dao_retrait.getSearch(id)){
            reponses = reponses + "{\"id_retrait\":\"";

                reponses = reponses + retrait.getIdRetrait();

                reponses = reponses + "\",\"montant_versement\":\"";

                reponses = reponses + 0;
                
                reponses = reponses + "\",\"montant_retrait\":\"";

                reponses = reponses + retrait.getMontant();

                reponses = reponses + "\",\"date_versement\":\"";

                reponses = reponses + retrait.getDate()+ "\"},";
        }
       
        if (reponses.charAt(reponses.length() - 1) == ',') {
            reponses = reponses.substring(0, reponses.length() - 1);
        }
        reponses = reponses + "]";
        reponses = reponses + "}";
        return reponses;
    }
}
