//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.SmsTransactionBo;
import com.appzone.sciencealerts.hibernate.dao.SmsTransactionDao;
import com.appzone.sciencealerts.hibernate.dao.impl.SmsTransactionDaoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsTransaction;

public class SmsTransactionBoImpl implements SmsTransactionBo {
    SmsTransactionDao smsTransactionDao = new SmsTransactionDaoImpl();

    public SmsTransactionBoImpl() {
    }

    public void save(SmsTransaction smsTransaction) {
        this.smsTransactionDao.save(smsTransaction);
    }

    public void update(SmsTransaction smsTransaction) {
        this.smsTransactionDao.update(smsTransaction);
    }

    public void delete(SmsTransaction smsTransaction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
