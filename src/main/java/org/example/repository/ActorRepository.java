package org.example.repository;

import org.example.entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ActorRepository {

    private final SessionFactory sessionFactory;

    public ActorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Actor create(Actor actor){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(actor);

        transaction.commit();
        session.close();

        return actor;
    }

    public List<Actor> findAll(){
        Session session = sessionFactory.openSession();

        List<Actor> actors = session.createQuery("from Actor", Actor.class).getResultList();

        session.close();
        return actors;
    }
}
