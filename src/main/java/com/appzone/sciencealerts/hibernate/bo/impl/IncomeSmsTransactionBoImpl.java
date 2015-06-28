//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.IncomeSmsTransactionBo;
import com.appzone.sciencealerts.hibernate.dao.IncomeSmsTransactionDao;
import com.appzone.sciencealerts.hibernate.dao.impl.IncomeSmsTransactionDaoImpl;
import com.appzone.sciencealerts.hibernate.model.IncomeSmsTransaction;

public class IncomeSmsTransactionBoImpl implements IncomeSmsTransactionBo {
    IncomeSmsTransactionDao incomeSmsTransactionDao = new IncomeSmsTransactionDaoImpl();

    public IncomeSmsTransactionBoImpl() {
    }

    public void save(IncomeSmsTransaction incomeSmsTransaction) {
        this.incomeSmsTransactionDao.save(incomeSmsTransaction);
    }

    public void update(IncomeSmsTransaction incomeSmsTransaction) {
        this.incomeSmsTransactionDao.update(incomeSmsTransaction);
    }

    public void delete(IncomeSmsTransaction incomeSmsTransaction) {
        this.incomeSmsTransactionDao.delete(incomeSmsTransaction);
    }
}
