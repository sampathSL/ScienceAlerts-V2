//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import com.appzone.sciencealerts.service.FinalSms;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class ScienceAlertsSenderInform {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    private static final Logger logger = Logger.getLogger(ScienceAlertsSenderInform.class);
    FinalSms transaction = new FinalSms();

    public ScienceAlertsSenderInform() {
    }

    public void Inform(String address, String[] split) {
        Date date = new Date();

        try {
            if(split.length > 3) {
                if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                    SmsSender e = this.smsSenderBo.findSmsSenderRecordById(Long.valueOf(Long.parseLong(split[2].toString())));
                    if(e != null && e.getAddress() != null) {
                        String scaInformStr = "";

                        for(int count = 3; count < split.length; ++count) {
                            scaInformStr = scaInformStr + " " + split[count].toString();
                        }

                        scaInformStr = scaInformStr.trim();
                        scaInformStr = scaInformStr.replace("null", "");
                        if(scaInformStr.length() < 160) {
                            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                            this.setResponceMsg(scaInformStr);
                            this.smsSender.sendMessage(this.getResponceMsg(), new String[]{e.getAddress()});
                            this.transaction.CollectData(e, this.getResponceMsg(), date);
                        } else {
                            logger.error("Inform Sms is too long ==============" + date);
                        }
                    } else {
                        logger.error("receiver is null ====================================" + date);
                    }
                } else {
                    logger.error("Address doesn\'t match ========Security Alert!!!=======" + date);
                }
            }
        } catch (MalformedURLException var7) {
            logger.error(var7);
            var7.printStackTrace();
        } catch (MchoiceAventuraMessagingException var8) {
            logger.error(var8);
            var8.printStackTrace();
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
