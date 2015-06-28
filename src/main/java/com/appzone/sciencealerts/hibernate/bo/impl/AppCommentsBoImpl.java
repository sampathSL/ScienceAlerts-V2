//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.bo.impl;

import com.appzone.sciencealerts.hibernate.bo.AppCommentsBo;
import com.appzone.sciencealerts.hibernate.dao.AppCommentsDao;
import com.appzone.sciencealerts.hibernate.dao.impl.AppCommentsDaoImpl;
import com.appzone.sciencealerts.hibernate.model.AppComments;

public class AppCommentsBoImpl implements AppCommentsBo {
    AppCommentsDao appCommentsDao = new AppCommentsDaoImpl();

    public AppCommentsBoImpl() {
    }

    public void save(AppComments appComments) {
        this.appCommentsDao.save(appComments);
    }

    public void update(AppComments appComments) {
        this.appCommentsDao.update(appComments);
    }

    public void delete(AppComments appComments) {
        this.appCommentsDao.delete(appComments);
    }

    public AppComments findAppCommentsById(Long id) {
        return this.appCommentsDao.findAppCommentsById(id);
    }
}
