//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.dao.ScienceAlertsDao;
import com.appzone.sciencealerts.hibernate.dao.impl.ScienceAlertsDaoImpl;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import java.util.List;

public class ScienceAlertsBoImpl implements ScienceAlertsBo {
    ScienceAlertsDao scienceAlertsDao = new ScienceAlertsDaoImpl();

    public ScienceAlertsBoImpl() {
    }

    public void save(ScienceAlerts scienceAlerts) {
        this.scienceAlertsDao.save(scienceAlerts);
    }

    public void update(ScienceAlerts scienceAlerts) {
        this.scienceAlertsDao.update(scienceAlerts);
    }

    public void delete(ScienceAlerts scienceAlerts) {
        this.scienceAlertsDao.delete(scienceAlerts);
    }

    public boolean checkIsScienceAlertExists(String title) {
        return this.scienceAlertsDao.checkIsScienceAlertExists(title);
    }

    public ScienceAlerts findScienceAlertRecordById(Long id) {
        return this.scienceAlertsDao.findScienceAlertById(id);
    }

    public ScienceAlerts getRandomScienceAlert() {
        return this.scienceAlertsDao.getRandomScienceAlert();
    }

    public List<ScienceAlerts> findScienceAlertByName(String name, String sql) {
        return this.scienceAlertsDao.findScienceAlertByName(name, sql);
    }

    public ScienceAlerts getAdminScienceAlert(Long alertId) {
        return this.scienceAlertsDao.getAdminScienceAlert(alertId);
    }

    public List<ScienceAlerts> getDailyScienceAlert() {
        return this.scienceAlertsDao.getDailyScienceAlert();
    }
}
