package com.companyname.dao;

import org.hibernate.Session;

import java.io.Closeable;

public interface SessionProvider extends Closeable {
    Session getSession();
    void closeSession();
}
