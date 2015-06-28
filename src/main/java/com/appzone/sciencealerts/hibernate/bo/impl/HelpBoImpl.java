//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.HelpBo;
import com.appzone.sciencealerts.hibernate.dao.HelpDao;
import com.appzone.sciencealerts.hibernate.dao.impl.HelpDaoImpl;
import com.appzone.sciencealerts.hibernate.model.Help;

public class HelpBoImpl implements HelpBo {
    HelpDao helpDao = new HelpDaoImpl();

    public HelpBoImpl() {
    }

    public void save(Help help) {
        this.helpDao.save(help);
    }

    public void update(Help help) {
        this.helpDao.update(help);
    }

    public void delete(Help help) {
        this.helpDao.delete(help);
    }
}
