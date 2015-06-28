//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao;

import com.appzone.sciencealerts.hibernate.model.AppComments;

public interface AppCommentsDao {
    void save(AppComments var1);

    void update(AppComments var1);

    void delete(AppComments var1);

    AppComments findAppCommentsById(Long var1);
}
