//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.HelpBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.HelpBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.Help;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class AccountInfo {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    private static final Logger logger = Logger.getLogger(AccountInfo.class);
    FinalSms transaction = new FinalSms();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    HelpBo helpBo;

    public AccountInfo() {
    }

    public void GetAccInfo(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            if(e != null) {
                String sender;
                MchoiceAventuraResponse daily_alerts;
                Help response;
                if(e.getIsReg().booleanValue()) {
                    sender = "";
                    if(e.getIsSchedularActive().booleanValue()) {
                        sender = "active.";
                    } else {
                        sender = "not active.";
                    }

                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("ACCOUNT_INFO_PART1_USER_NAME") + " " + e.getUserName() + PropertyFileReader.getValue("ACCOUNT_INFO_PART2_MARKS") + " " + e.getMarks() + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART3_JOINED_DATE") + this.dateformat.format(e.getJoinedDate()) + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART4_DAILY_ALERTS") + " " + sender);
                    daily_alerts = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!daily_alerts.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                } else {
                    e.setUserName("UNKNOWN");
                    e.setIsReg(Boolean.valueOf(true));
                    e.setIsActive(Boolean.valueOf(true));
                    e.setMarks(Long.valueOf(Long.parseLong("2")));
                    e.setIsSchedularActive(Boolean.valueOf(true));
                    e.setJoinedDate(date);
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    sender = "active.";
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("ACCOUNT_INFO_PART1_USER_NAME") + " " + e.getUserName() + PropertyFileReader.getValue("ACCOUNT_INFO_PART2_MARKS") + " " + e.getMarks() + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART3_JOINED_DATE") + this.dateformat.format(e.getJoinedDate()) + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART4_DAILY_ALERTS") + " " + sender);
                    daily_alerts = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!daily_alerts.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                }
            } else {
                SmsSender sender1 = new SmsSender();
                sender1.setAddress(address.toString());
                sender1.setUserName("UNKNOWN");
                sender1.setIsReg(Boolean.valueOf(true));
                sender1.setIsActive(Boolean.valueOf(true));
                sender1.setMarks(Long.valueOf(Long.parseLong("2")));
                sender1.setIsSchedularActive(Boolean.valueOf(true));
                sender1.setJoinedDate(date);
                sender1.setLastActiveTime(date);
                this.smsSenderBo.save(sender1);
                String daily_alerts1 = "active.";
                this.setResponceMsg(PropertyFileReader.getValue("ACCOUNT_INFO_PART1_USER_NAME") + " " + sender1.getUserName() + PropertyFileReader.getValue("ACCOUNT_INFO_PART2_MARKS") + " " + sender1.getMarks() + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART3_JOINED_DATE") + this.dateformat.format(sender1.getJoinedDate()) + " " + PropertyFileReader.getValue("ACCOUNT_INFO_PART4_DAILY_ALERTS") + " " + daily_alerts1);
                MchoiceAventuraResponse response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    Help help = new Help();
                    help.setHelpText(sender1.getId().toString());
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.CollectData(sender1, this.getResponceMsg(), date);
            }
        } catch (MalformedURLException var8) {
            logger.error(var8);
            var8.printStackTrace();
        } catch (MchoiceAventuraMessagingException var9) {
            logger.error(var9);
            var9.printStackTrace();
        } catch (Exception var10) {
            logger.error(var10);
            var10.printStackTrace();
        }

    }

    private String getResponceMsg() {
        return this.responceMsg;
    }

    private void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
