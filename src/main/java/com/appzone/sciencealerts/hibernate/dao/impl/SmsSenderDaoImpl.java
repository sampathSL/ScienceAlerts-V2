//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao.impl;

import com.appzone.sciencealerts.hibernate.dao.SmsSenderDao;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.hibernate.util.HibernateUtil;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class SmsSenderDaoImpl implements SmsSenderDao {
    private Session session;
    private static final Logger logger = Logger.getLogger(SmsSenderDaoImpl.class);

    public SmsSenderDaoImpl() {
    }

    public void save(SmsSender smsSender) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = this.session.beginTransaction();

        try {
            this.session.save(smsSender);
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

    public void update(SmsSender smsSender) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = this.session.beginTransaction();

        try {
            this.session.update(smsSender);
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

    public void delete(SmsSender smsSender) {
        try {
            this.update(smsSender);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public boolean checkIsSmsSenderExists(String userName) {
        boolean foundUserName = false;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Query query = this.session.createQuery("from SmsSender as sender where sender.userName=\'" + userName + "\'");
            List senderList = query.list();
            if(senderList.size() > 0) {
                foundUserName = true;
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var14) {
            ;
        } finally {
            try {
                this.session.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

        return foundUserName;
    }

    public SmsSender findSmsSenderRecordById(Long smsSenderId) {
        SmsSender smsSender = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Query query = this.session.createQuery("from SmsSender as sender where sender.id=" + smsSenderId);
            smsSender = (SmsSender)query.uniqueResult();
            Hibernate.initialize(smsSender);
            smsSender = (SmsSender)query.uniqueResult();
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

        return smsSender;
    }

    public SmsSender getRandomSmsSender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<SmsSender> findSmsSenderByName(String name, String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SmsSender getAdminSmsSender(Long smsSenderId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<SmsSender> getDailyAlertsSmsSenders() {
        List smsSenderList = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(SmsSender.class);
            crit.add(Restrictions.eq("isReg", Boolean.valueOf(true)));
            crit.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
            crit.add(Restrictions.eq("isSchedularActive", Boolean.valueOf(true)));
            crit.setMaxResults(Integer.parseInt(PropertyFileReader.getValue("SCHEDULE_NUMBER_OF_MAX_USERS")));
            smsSenderList = crit.list();
            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var11) {
                var11.printStackTrace();
            }

        }

        return smsSenderList;
    }

    public List<SmsSender> getAboutToDeactiveSmsSenders() {
        List smsSenderList = null;
        Date startDate = null;
        Date endDate = null;

        try {
            Calendar ex = Calendar.getInstance();
            ex.add(5, Integer.parseInt(PropertyFileReader.getValue("ADA_START_DATE")));
            startDate = ex.getTime();
            Calendar cEndDate = Calendar.getInstance();
            cEndDate.add(5, Integer.parseInt(PropertyFileReader.getValue("ADA_END_DATE")));
            endDate = cEndDate.getTime();
            logger.info(startDate.toString() + "===About To Deactivate startDate ===");
            logger.info(endDate.toString() + "===About To Deactivate endDate ===");
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(SmsSender.class);
            crit.add(Restrictions.eq("isReg", Boolean.valueOf(true)));
            crit.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
            crit.add(Restrictions.eq("isSchedularActive", Boolean.valueOf(true)));
            crit.add(Restrictions.between("lastActiveTime", startDate, endDate));
            crit.setMaxResults(Integer.parseInt(PropertyFileReader.getValue("SCHEDULE_NUMBER_OF_MAX_USERS")));
            smsSenderList = crit.list();
            transaction.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }

        return smsSenderList;
    }

    public List<SmsSender> getDailyDeactiveSmsSenders() {
        List smsSenderList = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Criteria crit = this.session.createCriteria(SmsSender.class);
            crit.add(Restrictions.eq("isReg", Boolean.valueOf(true)));
            crit.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
            crit.setMaxResults(Integer.parseInt(PropertyFileReader.getValue("SCHEDULE_NUMBER_OF_MAX_USERS")));
            smsSenderList = crit.list();
            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var11) {
                var11.printStackTrace();
            }

        }

        return smsSenderList;
    }

    public Long getSmsSenderRank(Long id) {
        Long smsSenderRank = new Long("0");

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Query query = this.session.createQuery("from SmsSender as sender where sender.isReg = 1 order by sender.marks DESC");
            List senderList = query.list();
            if(senderList.size() > 0) {
                for(long count = 0L; count < (long)senderList.size(); ++count) {
                    if(((SmsSender)senderList.get(Integer.parseInt(Long.toString(count)))).getId().equals(id)) {
                        smsSenderRank = Long.valueOf(count + 1L);
                    }
                }
            }

            ex.commit();
            this.session.flush();
            this.session.clear();
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                this.session.close();
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }

        return smsSenderRank;
    }

    public SmsSender findBySmsSenderAddress(String address) {
        SmsSender smsSender = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            Transaction ex = this.session.beginTransaction();
            Query query = this.session.createQuery("from SmsSender as sender where sender.address=\'" + address.toString() + "\'");
            smsSender = (SmsSender)query.uniqueResult();
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

        return smsSender;
    }
}
