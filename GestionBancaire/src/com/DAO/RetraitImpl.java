/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Client;
import com.Model.Retrait;
import com.Outil.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RAYA
 */
public class RetraitImpl implements RetraitDAO{

    @Override
    public List<Retrait> getAllRetrait() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria query = session.createCriteria(Retrait.class);
        return query.list();
    }

    @Override
    public void addRetrait(Retrait retrait) {
         SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(retrait);
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
    public void updateRetrait(Retrait retrait) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Retrait retrait_ = (Retrait) session.get(Retrait.class, retrait.getIdRetrait());
            retrait_.setClient(retrait.getClient());
            retrait_.setMontant(retrait.getMontant());
            retrait_.setDate(retrait.getDate());
            session.update(retrait_);
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
    public void deleteRetrait(Long id) {
         SessionFactory sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sf.openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            Retrait app = (Retrait) session.get(Retrait.class, id);
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
    public Retrait searchByID(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Retrait r where r.idRetrait =:id");
        query.setParameter("id", id);
        Iterator it = query.list().iterator();
        Retrait object = null ;
        while (it.hasNext()) {
             object = (Retrait) it.next();           
        }
        return object;
    }

    @Override
    public List<Retrait> getSearch(int id) {
        Client  client = new Client();
        client.setIdClient(id);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria query = session.createCriteria(Retrait.class)
                .add(Restrictions.eq("client", client));
        return query.list();
    }
    
}
