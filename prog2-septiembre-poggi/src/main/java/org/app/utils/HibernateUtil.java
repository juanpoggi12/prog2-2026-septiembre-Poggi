package org.app.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();

            String host = System.getenv().getOrDefault("DB_HOST", "localhost");
            String port = System.getenv().getOrDefault("DB_PORT", "3306");
            String dbName = System.getenv().getOrDefault("DB_NAME", "PROG2_SE26_Poggi");
            String user = System.getenv().getOrDefault("DB_USER", "root");
            String password = System.getenv().getOrDefault("DB_PASSWORD", "admin");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", user);
            configuration.setProperty("hibernate.connection.password", password);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DB", e);
        }
    }

    public static Session getSession() { return sessionFactory.openSession(); }
}