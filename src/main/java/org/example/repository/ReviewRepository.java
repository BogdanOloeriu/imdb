package org.example.repository;

import org.example.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewRepository implements CRUDRepository<Review>{

    private final SessionFactory sessionFactory;

    public ReviewRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Review create(Review review){
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

    @Override
    public List<Review> findAll() {
        Session session = sessionFactory.openSession();

        List<Review> reviews = session.createQuery("from Review", Review.class).getResultList();

        session.close();
        return reviews;
    }
}
