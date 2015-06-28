//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.ScienceAlertsBoImpl;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class ScienceAlertsAdminGet {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo;
    ScienceAlertsBo scienceAlertsBo;
    private static final Logger logger = Logger.getLogger(ScienceAlertsAdminGet.class);

    public ScienceAlertsAdminGet() {
    }

    public void getAdminScienceAlert(String address, String[] adminAlert) {
        try {
            if(adminAlert.length > 2 && adminAlert.length < 4) {
                Date e = new Date();
                if(!address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS")) && !address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS2"))) {
                    logger.error("Address doesn\'t match ========Security Alert!!!=======" + e);
                } else {
                    try {
                        if(adminAlert[2] != null) {
                            this.scienceAlertsBo = new ScienceAlertsBoImpl();
                            ScienceAlerts e1 = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(adminAlert[2].toString())));
                            this.setResponceMsg(e1.getSms());
                            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                            this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        } else {
                            logger.error("null number");
                        }
                    } catch (MalformedURLException var5) {
                        logger.error(var5);
                        var5.printStackTrace();
                    } catch (MchoiceAventuraMessagingException var6) {
                        logger.error(var6);
                        var6.printStackTrace();
                    } catch (Exception var7) {
                        logger.error(var7);
                        var7.printStackTrace();
                    }
                }
            }
        } catch (Exception var8) {
            logger.error(var8);
            var8.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
