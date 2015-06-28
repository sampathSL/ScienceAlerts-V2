//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.ScienceAlertsDao;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ScienceAlertsDaoImpl implements ScienceAlertsDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(ScienceAlertsDaoImpl.class);

    public ScienceAlertsDaoImpl() {
    }

    public void save(ScienceAlerts scienceAlerts) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = this.session.beginTransaction();

        try {
            this.session.save(scienceAlerts);
            transaction.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var15) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception var14) {
                logger.error(var14);
            }
        } finally {
            try {
                this.session.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

    }

    public void update(ScienceAlerts scienceAlerts) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = this.session.beginTransaction();

        try {
            this.session.update(scienceAlerts);
            transaction.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var15) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception var14) {
                logger.error(var14);
            }
        } finally {
            try {
                this.session.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

    }

    public void delete(ScienceAlerts scienceAlerts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean checkIsScienceAlertExists(String title) {
        boolean foundTitle = false;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            if(title != null && !"".equalsIgnoreCase(title)) {
                Criteria e = this.session.createCriteria(ScienceAlerts.class);
                e.add(Restrictions.eq("title", title));
                List senderList = e.list();
                if(senderList.size() > 0) {
                    foundTitle = true;
                }
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var7) {
            try {
                this.session.close();
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return foundTitle;
    }

    public ScienceAlerts findScienceAlertById(Long id) {
        ScienceAlerts scienceDailyAlert = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(ScienceAlerts.class);
            crit.add(Restrictions.eq("id", id));
            if(crit.list().size() > 0) {
                scienceDailyAlert = (ScienceAlerts)crit.uniqueResult();
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

        return scienceDailyAlert;
    }

    public ScienceAlerts getRandomScienceAlert() {
        ScienceAlerts scienceAlert = new ScienceAlerts();

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(ScienceAlerts.class);
            crit.setProjection(Projections.rowCount());
            int count = ((Number)crit.uniqueResult()).intValue();
            if(0 != count) {
                int index = (new Random()).nextInt(count);
                crit = this.session.createCriteria(ScienceAlerts.class);
                scienceAlert = (ScienceAlerts)crit.setFirstResult(index).setMaxResults(1).uniqueResult();
            } else {
                scienceAlert.setSms("Unexpected Error Occured!!!");
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

        return scienceAlert;
    }

    public List<ScienceAlerts> findScienceAlertByName(String name, String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ScienceAlerts getAdminScienceAlert(Long alertId) {
        ScienceAlerts scienceDailyAlert = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(ScienceAlerts.class);
            crit.add(Restrictions.eq("id", Long.valueOf(alertId.longValue())));
            crit.setMaxResults(1);
            if(crit.uniqueResult() != null) {
                scienceDailyAlert = (ScienceAlerts)crit.uniqueResult();
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

        return scienceDailyAlert;
    }

    public List<ScienceAlerts> getDailyScienceAlert() {
        List scienceDailyAlert = null;

        try {
            Calendar ex = Calendar.getInstance();
            ex.add(5, Integer.parseInt(PropertyFileReader.getValue("DAILY_ALERTS_SET_DATE")));
            Date startDate = ex.getTime();
            ex.add(5, 1);
            Date endDate = ex.getTime();
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(ScienceAlerts.class);
            crit.add(Restrictions.eq("scheduled", Boolean.valueOf(false)));
            crit.add(Restrictions.between("publishDate", startDate, endDate));
            crit.addOrder(Order.asc("id"));
            crit.setMaxResults(3);
            if(crit.list().size() > 0) {
                scienceDailyAlert = crit.list();
            }

            transaction.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var16) {
                var16.printStackTrace();
            }

        }

        return scienceDailyAlert;
    }
}
