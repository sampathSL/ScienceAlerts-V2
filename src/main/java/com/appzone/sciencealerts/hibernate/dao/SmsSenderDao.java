//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.dao;

import com.appzone.sciencealerts.hibernate.model.SmsSender;
import java.util.List;

public interface SmsSenderDao {
    void save(SmsSender var1);

    void update(SmsSender var1);

    void delete(SmsSender var1);

    boolean checkIsSmsSenderExists(String var1);

    SmsSender findSmsSenderRecordById(Long var1);

    SmsSender getRandomSmsSender();

    List<SmsSender> findSmsSenderByName(String var1, String var2);

    SmsSender getAdminSmsSender(Long var1);

    List<SmsSender> getDailyAlertsSmsSenders();

    List<SmsSender> getAboutToDeactiveSmsSenders();

    List<SmsSender> getDailyDeactiveSmsSenders();

    Long getSmsSenderRank(Long var1);

    SmsSender findBySmsSenderAddress(String var1);
}
