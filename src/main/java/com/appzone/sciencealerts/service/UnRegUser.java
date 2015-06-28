//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.service.FinalSms;
import java.util.Date;
import org.apache.log4j.Logger;

public class UnRegUser {
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    FinalSms transaction = new FinalSms();
    private static final Logger logger = Logger.getLogger(UnRegUser.class);

    public UnRegUser() {
    }

    public void ScienceAlertUnReg(String address) {
        Date date = new Date();

        try {
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            if(e != null) {
                e.setIsReg(Boolean.valueOf(false));
                e.setIsActive(Boolean.valueOf(false));
                e.setIsSchedularActive(Boolean.valueOf(false));
                e.setLastActiveTime(date);
                this.smsSenderBo.delete(e);
            } else {
                SmsSender sender = new SmsSender();
                sender.setAddress(address.toString());
                sender.setUserName("UNKNOWN");
                sender.setIsReg(Boolean.valueOf(false));
                sender.setIsActive(Boolean.valueOf(false));
                sender.setMarks(Long.valueOf(Long.parseLong("2")));
                sender.setIsSchedularActive(Boolean.valueOf(false));
                sender.setJoinedDate(date);
                sender.setLastActiveTime(date);
                this.smsSenderBo.save(sender);
            }
        } catch (Exception var5) {
            logger.error(var5);
            var5.printStackTrace();
        }

    }
}
