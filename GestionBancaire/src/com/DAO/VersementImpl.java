/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Client;
import com.Model.Versement;
import com.Outil.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RAYA
 */
public class VersementImpl implements VersementDAO{

    @Override
    public List<Versement> getVersement() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria query = session.createCriteria(Versement.class);
        return query.list();
    }

    @Override
    public void addVersement(Versement versement) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(versement);
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
    public void updateVersement(Versement versement) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Versement versement_ = (Versement) session.get(Versement.class, versement.getIdVersement());
            versement_.setClient(versement.getClient());
            versement_.setMontant(versement.getMontant());
            versement_.setDate(versement.getDate());
            session.update(versement_);
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
    public void deleteVersement(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Versement app = (Versement) session.get(Versement.class, id);
            session.delete(app);
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
    public List<Versement> getsearch(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Client client = new Client();
        client.setIdClient(id);
        Criteria query = session.createCriteria(Versement.class)
                .add(Restrictions.eq("client", client));        
        return query.list();
    }
    
}
