/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Client;
import com.Outil.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RAYA
 */
public class ClientImpl implements ClientDAO{

    @Override
    public List<Client> getAllClient() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria query = session.createCriteria(Client.class);
        return query.list();
    }

    @Override
    public void addClient(Client client) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(client);
            session.flush();
            tr.commit();
            session.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
        }
    }

    @Override
    public void updateClient(Client client) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Client client_ = (Client) session.get(Client.class, client.getIdClient());
            client_.setNomClient(client.getNomClient());
            client_.setSolde(client.getSolde());
            session.update(client_);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteClient(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Client cl = (Client) session.get(Client.class, id);
            session.delete(cl);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Client> getAllClientSearch(String search, String choix) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria query = null;
        if(choix.equalsIgnoreCase("Nom")){
            query = session.createCriteria(Client.class)
                    .add(Restrictions.like("nomClient", search));
        }else if(choix.equalsIgnoreCase("N° compte")){
           query = session.createCriteria(Client.class)
                   .add(Restrictions.like("idClient", Integer.parseInt(search)));
        }else{
            query = session.createCriteria(Client.class);
        }             
        return query.list();
    }

    @Override
    public Client searchByID(String nom) {        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Client c where c.nomClient =:nom");
        query.setParameter("nom", nom);
        Iterator it = query.list().iterator();
        Client object = null ;
        while (it.hasNext()) {
             object = (Client) it.next();           
        }
        return object;
    }

    @Override
    public Integer CompteClient() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List resultats = session.createCriteria(Client.class)
            .setProjection(Projections.rowCount())
            .list();
            Integer valeur = (Integer) resultats.get(0);
            //System.out.println("nb personnes = " + valeur);
        return valeur;
    }

   @Override
    public Client searchBy(int id) {        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Client c where c.idClient =:id");
        query.setParameter("id", id);
        Iterator it = query.list().iterator();
        Client object = null ;
        while (it.hasNext()) {
             object = (Client) it.next();           
        }
        return object;
    }
    
}
