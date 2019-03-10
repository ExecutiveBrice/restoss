package com.brice.corp.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.brice.corp.model.Utilisateur;


@Repository("utilisateurDao")
public class UtilisateurDao extends AbstractDao<Integer, Utilisateur> {

    public Utilisateur findById(int id) {
        Utilisateur utilisateur = getByKey(id);
        if(utilisateur!=null){
            initializeCollection(utilisateur.getUtilisateurProfiles());
        }
        System.out.println(utilisateur);
        return utilisateur;
    }

    public Utilisateur findBySso(String sso) {
        System.out.println("SSO : "+sso);
        try{
            Utilisateur utilisateur = (Utilisateur) getEntityManager()
                    .createQuery("SELECT u FROM Utilisateur u WHERE u.ssoId LIKE :ssoId")
                    .setParameter("ssoId", sso)
                    .getSingleResult();
            System.out.println(utilisateur);
            if(utilisateur!=null){
                initializeCollection(utilisateur.getUtilisateurProfiles());
            }
            return utilisateur;
        }catch(NoResultException ex){
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    public List<Integer> findAllUtilisateursActifs() {
        List<Integer> utilisateurs = getEntityManager()
                .createQuery("SELECT u.id FROM Utilisateur u WHERE state=:state ORDER BY u.firstName ASC")
                .setParameter("state", "Active")
                .getResultList();
        return utilisateurs;
    }

    @SuppressWarnings("unchecked")
    public List<Integer> findAllUtilisateurs() {
        List<Integer> utilisateurs = getEntityManager()
                .createQuery("SELECT u.id FROM Utilisateur u ORDER BY u.firstName ASC")
                .getResultList();
        return utilisateurs;
    }

    public void save(Utilisateur utilisateur) {
        persist(utilisateur);
    }

    public void deleteBySSO(String sso) {
        Utilisateur utilisateur = (Utilisateur) getEntityManager()
                .createQuery("SELECT u FROM Utilisateur u WHERE u.ssoId LIKE :ssoId")
                .setParameter("ssoId", sso)
                .getSingleResult();
        delete(utilisateur);
    }

    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }

}
