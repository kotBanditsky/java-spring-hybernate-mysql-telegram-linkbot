package com.example.javaspringmysqltelegramlinkbot;

import com.example.javaspringmysqltelegramlinkbot.entity.Links;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Random;

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
    }

    public static void addBase(String wikiLink) {
            SessionFactory sessionFactory = Dbconnect.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            Links link = new Links("https://lold.lol", 124);
            session.beginTransaction();

            session.save(link);
            session.getTransaction().commit();

            System.out.println(link);
            System.out.println("Done!");
    }

    public static String fromBase() {
        SessionFactory sessionFactory = Dbconnect.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Long count = ((Long) session.createQuery("select count(*) from Links").uniqueResult());
        Integer totalLink = count.intValue();

        final Random random = new Random();
        int randomLinkNumber = random.nextInt(totalLink) + 1;

        String funLink = String.valueOf(session.createQuery("select link from Links where id =" + randomLinkNumber).uniqueResult());;
        session.getTransaction().commit();
        return funLink;
    }

}
