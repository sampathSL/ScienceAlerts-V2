//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.dao.SmsSenderDao;
import com.appzone.sciencealerts.hibernate.dao.impl.SmsSenderDaoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import java.util.List;

public class SmsSenderBoImpl implements SmsSenderBo {
    SmsSenderDao smsSenderDao = new SmsSenderDaoImpl();

    public SmsSenderBoImpl() {
    }

    public void save(SmsSender smsSender) {
        this.smsSenderDao.save(smsSender);
    }

    public void update(SmsSender smsSender) {
        this.smsSenderDao.update(smsSender);
    }

    public void delete(SmsSender smsSender) {
        this.smsSenderDao.delete(smsSender);
    }

    public boolean checkIsSmsSenderExists(String name) {
        return this.smsSenderDao.checkIsSmsSenderExists(name);
    }

    public SmsSender findSmsSenderRecordById(Long id) {
        return this.smsSenderDao.findSmsSenderRecordById(id);
    }

    public SmsSender getRandomSmsSender() {
        return this.smsSenderDao.getRandomSmsSender();
    }

    public List<SmsSender> findSmsSenderByName(String name, String sql) {
        return this.smsSenderDao.findSmsSenderByName(name, sql);
    }

    public SmsSender getAdminSmsSender(Long smsSenderId) {
        return this.smsSenderDao.getAdminSmsSender(smsSenderId);
    }

    public List<SmsSender> getDailyAlertsSmsSenders() {
        return this.smsSenderDao.getDailyAlertsSmsSenders();
    }

    public List<SmsSender> getAboutToDeactiveSmsSenders() {
        return this.smsSenderDao.getAboutToDeactiveSmsSenders();
    }

    public List<SmsSender> getDailyDeactiveSmsSenders() {
        return this.smsSenderDao.getDailyDeactiveSmsSenders();
    }

    public Long getSmsSenderRank(Long id) {
        return this.smsSenderDao.getSmsSenderRank(id);
    }

    public SmsSender findBySmsSenderAddress(String address) {
        return this.smsSenderDao.findBySmsSenderAddress(address);
    }
}
