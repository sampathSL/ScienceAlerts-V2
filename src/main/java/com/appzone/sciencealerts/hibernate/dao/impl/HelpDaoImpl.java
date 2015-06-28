//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.HelpDao;
import com.appzone.sciencealerts.hibernate.model.Help;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class HelpDaoImpl implements HelpDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(HelpDaoImpl.class);

    public HelpDaoImpl() {
    }

    public void save(Help help) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.save(help);
                ex.commit();
                this.session.flush();
                this.session.clear();
            } catch (Exception var16) {
                try {
                    if(ex != null) {
                        ex.rollback();
                    }
                } catch (Exception var15) {
                    logger.error(var15);
                }
            }
        } catch (Exception var17) {
            logger.error(var17);
        } finally {
            try {
                this.session.close();
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }

    }

    public void update(Help help) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.update(help);
                ex.commit();
                this.session.flush();
                this.session.clear();
            } catch (Exception var16) {
                try {
                    if(ex != null) {
                        ex.rollback();
                    }
                } catch (Exception var15) {
                    logger.error(var15);
                }
            }
        } catch (Exception var17) {
            logger.error(var17);
        } finally {
            try {
                this.session.close();
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }

    }

    public void delete(Help help) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
