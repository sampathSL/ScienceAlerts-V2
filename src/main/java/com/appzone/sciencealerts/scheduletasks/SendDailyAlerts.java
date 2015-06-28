//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.scheduletasks;

import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.ScienceAlertsBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class SendDailyAlerts {
    private MchoiceAventuraSmsSender dailySmsSender;
    private String responceMsg = "";
    ScienceAlertsBo scienceAlertsBo;
    SmsSenderBo smsSenderBo;
    private static final Logger logger = Logger.getLogger(SendDailyAlerts.class);

    public SendDailyAlerts() {
    }

    public void execute() {
        this.SendDailyAlerts();
    }

    private void SendDailyAlerts() {
        try {
            SimpleDateFormat ex = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            this.smsSenderBo = new SmsSenderBoImpl();
            this.scienceAlertsBo = new ScienceAlertsBoImpl();
            List dailyList = this.smsSenderBo.getDailyAlertsSmsSenders();
            List dailyScienceAlerts = this.scienceAlertsBo.getDailyScienceAlert();
            if(dailyList != null) {
                if(dailyList.size() > 0) {
                    if(dailyScienceAlerts != null) {
                        if(dailyScienceAlerts.size() > 0) {
                            try {
                                logger.info("senderList.size() is ========== " + dailyList.size());

                                for(int ex1 = 0; ex1 < dailyList.size(); ++ex1) {
                                    String sa = ((SmsSender)dailyList.get(ex1)).getAddress();

                                    try {
                                        Iterator ex2 = dailyScienceAlerts.iterator();

                                        while(ex2.hasNext()) {
                                            ScienceAlerts sa1 = (ScienceAlerts)ex2.next();
                                            if(sa1.getSms() != null) {
                                                this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                this.setResponceMsg(sa1.getSms().toString());
                                                this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{sa});
                                            }
                                        }
                                    } catch (MchoiceAventuraMessagingException var9) {
                                        logger.error(var9);
                                    } catch (Exception var10) {
                                        logger.error(var10);
                                        var10.printStackTrace();
                                    }
                                }

                                Iterator var13 = dailyScienceAlerts.iterator();

                                while(var13.hasNext()) {
                                    ScienceAlerts var14 = (ScienceAlerts)var13.next();
                                    var14.setScheduled(Boolean.valueOf(true));
                                    this.scienceAlertsBo.update(var14);
                                }

                                logger.info("Daily SMS Sending Success AT ========== " + ex.format(date) + " Number of smses Sent = " + dailyList.size() * dailyScienceAlerts.size());
                            } catch (Exception var11) {
                                logger.error(var11);
                                var11.printStackTrace();
                            }
                        } else {
                            logger.info("dailyScienceAlerts.size() is 0");
                        }
                    } else {
                        logger.info("dailyScienceAlerts.getSms() is null");
                    }
                } else {
                    logger.info("dailyList.size() is 0");
                }
            } else {
                logger.info("dailyList.size() is null");
            }
        } catch (Exception var12) {
            logger.error(var12);
            var12.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
