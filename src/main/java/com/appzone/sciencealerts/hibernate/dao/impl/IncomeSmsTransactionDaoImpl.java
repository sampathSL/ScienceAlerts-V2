//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.IncomeSmsTransactionDao;
import com.appzone.sciencealerts.hibernate.model.IncomeSmsTransaction;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IncomeSmsTransactionDaoImpl implements IncomeSmsTransactionDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(IncomeSmsTransactionDaoImpl.class);

    public IncomeSmsTransactionDaoImpl() {
    }

    public void save(IncomeSmsTransaction incomeSmsTransaction) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.save(incomeSmsTransaction);
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

    public void update(IncomeSmsTransaction incomeSmsTransaction) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.update(incomeSmsTransaction);
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

    public void delete(IncomeSmsTransaction incomeSmsTransaction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
