/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Retrait;
import java.util.List;

/**
 *
 * @author RAYA
 */
public interface RetraitDAO {

    List<Retrait> getAllRetrait();

    void addRetrait(Retrait retrait);

    void updateRetrait(Retrait retrait);

    void deleteRetrait(Long id);
    
    Retrait searchByID(Long id);
    
    List<Retrait> getSearch(int id);
}
