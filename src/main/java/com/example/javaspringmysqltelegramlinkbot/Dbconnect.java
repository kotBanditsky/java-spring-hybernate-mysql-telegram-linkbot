package com.example.javaspringmysqltelegramlinkbot;

import com.example.javaspringmysqltelegramlinkbot.entity.Links;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Dbconnect {

    public static SessionFactory factory;

    private Dbconnect() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Links.class)
                    .buildSessionFactory();
        }
        return factory;
    }

    public static void main(String[] args) {
        addBase();
        fromBase();
    }

    private static void addBase() {
            SessionFactory sessionFactory = Dbconnect.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            Links link = new Links("https://lold.lol", 124);
            session.beginTransaction();
            session.save(link);
            session.getTransaction().commit();
            System.out.println(link);
            System.out.println("Done!");
    }

    private static void fromBase() {
            SessionFactory sessionFactory = Dbconnect.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<Links> links = session.createQuery("from Links " + "where id < 650").getResultList();

            for (Links e: links) {
                    System.out.println(e);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
    }

}
