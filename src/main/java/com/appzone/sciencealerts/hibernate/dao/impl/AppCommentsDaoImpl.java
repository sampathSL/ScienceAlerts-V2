//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.AppCommentsDao;
import com.appzone.sciencealerts.hibernate.model.AppComments;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppCommentsDaoImpl implements AppCommentsDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(AppCommentsDaoImpl.class);

    public AppCommentsDaoImpl() {
    }

    public void save(AppComments appComments) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.saveOrUpdate(appComments);
                ex.commit();
                this.session.flush();
                this.session.clear();
            } catch (Exception var16) {
                try {
                    if(ex != null) {
                        ex.rollback();
                    }
                } catch (Exception var15) {
                    logger.error(var16);
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

    public void update(AppComments appComments) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();

            try {
                this.session.update(appComments);
                ex.commit();
                this.session.flush();
                this.session.clear();
            } catch (Exception var16) {
                try {
                    if(ex != null) {
                        ex.rollback();
                    }
                } catch (Exception var15) {
                    logger.error(var16);
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

    public void delete(AppComments appComments) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AppComments findAppCommentsById(Long appCommentId) {
        AppComments appComments = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Query query = this.session.createQuery("from AppComments as comments where comments.id=" + appCommentId);
            appComments = (AppComments)query.uniqueResult();
            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var13) {
            logger.error(var13);
            var13.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            return appComments;
        }
    }
}
