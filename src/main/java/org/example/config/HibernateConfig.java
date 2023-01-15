package org.example.config;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getHibernateSessionFactory(){
        if(sessionFactory == null){
            System.out.println("Creating session factory!");
           sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Movie.class)
                    .addAnnotatedClass(AppUser.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Tag.class)
                   .addAnnotatedClass(Casting.class)
                   .addAnnotatedClass(Actor.class)
                    .buildSessionFactory();
            System.out.println("Session factory created!");
        }
        return sessionFactory;
    }

    public static void closeHibernateSessionFactory(){
        if(sessionFactory != null){
            System.out.println("Closing sessionFactory!");
            sessionFactory.close();
            System.out.println("Session Factory closed!");
        }
    }
}
