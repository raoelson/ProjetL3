/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Client;
import java.util.List;

/**
 *
 * @author RAYA
 */
public interface ClientDAO {

    List<Client> getAllClient();

    void addClient(Client client);

    void updateClient(Client client);

    void deleteClient(int id);

    List<Client> getAllClientSearch(String search, String choix);

    Client searchByID(String nom);

    Integer CompteClient();
    
    Client searchBy(int id);
    
}
