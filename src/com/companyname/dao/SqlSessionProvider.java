package com.companyname.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.IOException;

public class SqlSessionProvider implements SessionProvider {

    private Session readSession;
    private SessionFactory sessionFactory;

    public SqlSessionProvider(String hibernateCfgFile) {
        System.out.println("loading hibernate config (faked): " + hibernateCfgFile);

        Configuration configuration = new Configuration();
        configuration.configure(hibernateCfgFile);
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();
        /*
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        */
    }

    @Override
    public Session getSession() {
        if (this.readSession == null || ! this.readSession.isOpen()) {
            this.readSession = sessionFactory.openSession();
        }

        return readSession;
    }

    @Override
    public void closeSession() {
        if (readSession != null) {
            readSession.close();
            readSession = null;
        }
    }

    private void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Override
    public void close() throws IOException {
        closeSession();
        closeSessionFactory();
    }
}
