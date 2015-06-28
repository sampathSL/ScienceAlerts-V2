//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.SmsTransactionDao;
import com.appzone.sciencealerts.hibernate.model.SmsTransaction;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SmsTransactionDaoImpl implements SmsTransactionDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(SmsTransactionDaoImpl.class);

    public SmsTransactionDaoImpl() {
    }

    public void save(SmsTransaction smsTransaction) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.save(smsTransaction);
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

    public void update(SmsTransaction smsTransaction) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.update(smsTransaction);
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

    public void delete(SmsTransaction smsTransaction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
