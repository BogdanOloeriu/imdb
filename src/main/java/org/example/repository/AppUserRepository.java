package org.example.repository;

import org.example.entity.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppUserRepository {

    private final SessionFactory sessionFactory;

    public AppUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public AppUser saveAppUser(AppUser appUser){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.persist(appUser);

            transaction.commit();
            session.close();

            return appUser;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
