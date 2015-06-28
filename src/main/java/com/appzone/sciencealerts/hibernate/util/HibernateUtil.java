//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        try {
            sessionFactory = (new AnnotationConfiguration()).configure().buildSessionFactory();
        } catch (ExceptionInInitializerError var1) {
            logger.error(var1);
            throw new ExceptionInInitializerError(var1);
        }
    }
}
