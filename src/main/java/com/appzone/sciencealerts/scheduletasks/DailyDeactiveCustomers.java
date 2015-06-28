//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.scheduletasks;

import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.dao.impl.SmsSenderDaoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import com.appzone.sciencealerts.service.ScienceAlert;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyDeactiveCustomers implements Job {
    private String responceMsg = "";
    private MchoiceAventuraSmsSender deactiveSmsSender;
    SmsSenderBo smsSenderBo;
    private static final Logger logger = Logger.getLogger(DailyDeactiveCustomers.class);

    public DailyDeactiveCustomers() {
    }

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        this.DeactiveDailyAlerts();
    }

    private void DeactiveDailyAlerts() {
        try {
            SimpleDateFormat ex = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            this.smsSenderBo = new SmsSenderBoImpl();
            List deactivateList = this.smsSenderBo.getDailyDeactiveSmsSenders();
            this.setResponceMsg(PropertyFileReader.getValue("DAILY_DEACTIVE_SMS") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + " " + PropertyFileReader.getValue("EXAMPLE_ACT_SCHEDULE"));
            if(deactivateList != null) {
                if(deactivateList.size() > 0) {
                    logger.info("aboutToDeactivateList.size() is = " + deactivateList.size());

                    for(int count = 0; count < deactivateList.size(); ++count) {
                        try {
                            this.deactiveSmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                            this.deactiveSmsSender.sendMessage(this.getResponceMsg(), new String[]{((SmsSender)deactivateList.get(count)).getAddress().toString()});
                            SmsSenderDaoImpl ex1 = new SmsSenderDaoImpl();
                            SmsSender smsSender = (SmsSender)deactivateList.get(count);
                            smsSender.setIsSchedularActive(Boolean.valueOf(false));
                            smsSender.setLastActiveTime(date);
                            ex1.update(smsSender);
                        } catch (MchoiceAventuraMessagingException var7) {
                            java.util.logging.Logger.getLogger(ScienceAlert.class.getName()).log(Level.SEVERE, (String)null, var7);
                        } catch (Exception var8) {
                            logger.error(var8);
                            var8.printStackTrace();
                        }
                    }

                    logger.info("Daily About To Deactivate Sending Success AT " + ex.format(date));
                } else {
                    logger.info("DeactivateList.size() is 0");
                }
            } else {
                logger.info("DeactivateList.size() is null");
            }
        } catch (Exception var9) {
            logger.error(var9);
            var9.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
