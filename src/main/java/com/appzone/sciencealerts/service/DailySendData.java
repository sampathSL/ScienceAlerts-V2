//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.HelpBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.HelpBoImpl;
import com.appzone.sciencealerts.hibernate.model.Help;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import com.appzone.sciencealerts.scheduletasks.SendDailyAlerts;
import com.appzone.sciencealerts.service.FinalSms;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class DailySendData {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo;
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(DailySendData.class);
    FinalSms transaction = new FinalSms();

    public DailySendData() {
    }

    public void SendData(String address) {
        Date date = new Date();

        try {
            if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                SendDailyAlerts e = new SendDailyAlerts();
                e.execute();
                this.setResponceMsg("Data Sent Success");
                MchoiceAventuraResponse response = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response.isSuccess()) {
                    Help help = new Help();
                    help.setHelpText(address);
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            } else {
                logger.error("Address doesn\'t match ========Security Alert!!!=======" + date);
            }
        } catch (MchoiceAventuraMessagingException var6) {
            logger.error(var6);
            var6.printStackTrace();
        } catch (MalformedURLException var7) {
            logger.error(var7);
            var7.printStackTrace();
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
