package org.example.repository;

import org.example.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ReviewRepository {

    private final SessionFactory sessionFactory;

    public ReviewRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Review saveReview(Review review){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.persist(review);

            transaction.commit();
            session.close();

            return review;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
