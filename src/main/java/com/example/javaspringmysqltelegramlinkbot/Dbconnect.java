package com.example.javaspringmysqltelegramlinkbot;

import com.example.javaspringmysqltelegramlinkbot.entity.Links;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Dbconnect {

    public static void main(String[] args) {
    }

    public static String addBase(String wikiLink) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Links.class)
                .buildSessionFactory();

        Session session = null;
        String answer;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<String> funs = session.createQuery("select link from Links").getResultList();

            if (funs.contains(wikiLink)) {
                answer = "This link is already in the database!";
            } else {
                answer = "The link was successfully added to the database!";
                Links link = new Links(wikiLink, 0);
                session.save(link);
                session.getTransaction().commit();
            }

        } finally {
            factory.close();
        }
        return answer;
    }

    public static String fromBase() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Links.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Long count = ((Long) session.createQuery("select count(*) from Links").uniqueResult());
            Integer totalLink = count.intValue();

            final Random random = new Random();
            int randomLinkNumber = random.nextInt(totalLink) + 1;

            String funLink = String.valueOf(session.createQuery("select link from Links where id =" + randomLinkNumber).uniqueResult());
            session.getTransaction().commit();
            return funLink;

        } finally {
            factory.close();
        }

    }

    public static String rateLink(String call_message_text, String call_data) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Links.class)
                .buildSessionFactory();

        Session session = null;
        int rate = 0;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            int ides = (int) session.createQuery("select id from Links where link = :call_message_text").setParameter("call_message_text", call_message_text).uniqueResult();

            if (call_data.equals(Config.VOTE_UP)) {
                session.createQuery("update Links set rate = rate + 1 where id = " + ides).executeUpdate();
                rate = (int) session.createQuery("select rate from Links where id = " + ides).uniqueResult();
            }

            if (call_data.equals(Config.VOTE_DOWN)) {
                session.createQuery("update Links set rate = rate - 1 where id = " + ides).executeUpdate();
                rate = (int) session.createQuery("select rate from Links where id = " + ides).uniqueResult();
            }

            session.getTransaction().commit();
            return String.valueOf(rate);

        } finally {
            factory.close();
        }
    }

    public static String getTop() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Links.class)
                .buildSessionFactory();

        Session session = null;
        int rate = 0;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            List<Links> rates = session.createQuery("from Links").getResultList();

            rates.sort(Comparator.comparing(Links::getRate).reversed());

            int count = 1;
            StringBuffer sBuffer = new StringBuffer("Top rated Wiki articles:" + System.lineSeparator() + System.lineSeparator());

            for (int i = 0; i < 10; i++){
                sBuffer.append(count + ". " + rates.get(i).getLink() + " Rate: "+ rates.get(i).getRate() + System.lineSeparator());
                count++;
            }

            session.getTransaction().commit();
            return String.valueOf(sBuffer);

        } finally {
            factory.close();
        }
    }

}
