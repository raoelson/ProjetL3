/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Versement;
import java.util.List;

/**
 *
 * @author RAYA
 */
public interface VersementDAO {

    List<Versement> getVersement();

    void addVersement(Versement versement);

    void updateVersement(Versement versement);

    void deleteVersement(Long id);
    
    List<Versement> getsearch(int id);
}
