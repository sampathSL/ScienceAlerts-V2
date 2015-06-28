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
import com.appzone.sciencealerts.service.FinalSms;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class AccountRank {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(AccountRank.class);
    FinalSms transaction = new FinalSms();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    Date date = new Date();

    public AccountRank() {
    }

    public void GetAccRank(String address) {
        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            if(e != null) {
                MchoiceAventuraResponse sender;
                Help response;
                if(e.getIsReg().booleanValue()) {
                    e.setLastActiveTime(this.date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("RANK_INFO") + " " + this.smsSenderBo.getSmsSenderRank(e.getId()) + PropertyFileReader.getValue("RANK_INFO_MORE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + " " + PropertyFileReader.getValue("RANK_INFO_RETRIC"));
                    sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), this.date);
                } else {
                    e.setUserName("UNKNOWN");
                    e.setIsReg(Boolean.valueOf(true));
                    e.setIsActive(Boolean.valueOf(true));
                    e.setMarks(Long.valueOf(Long.parseLong("2")));
                    e.setIsSchedularActive(Boolean.valueOf(true));
                    e.setJoinedDate(this.date);
                    e.setLastActiveTime(this.date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("RANK_INFO") + " " + this.smsSenderBo.getSmsSenderRank(e.getId()) + PropertyFileReader.getValue("RANK_INFO_MORE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + " " + PropertyFileReader.getValue("RANK_INFO_RETRIC"));
                    sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), this.date);
                }
            } else {
                SmsSender sender1 = new SmsSender();
                sender1.setAddress(address.toString());
                sender1.setUserName("UNKNOWN");
                sender1.setIsReg(Boolean.valueOf(true));
                sender1.setIsActive(Boolean.valueOf(true));
                sender1.setMarks(Long.valueOf(Long.parseLong("2")));
                sender1.setIsSchedularActive(Boolean.valueOf(true));
                sender1.setJoinedDate(this.date);
                sender1.setLastActiveTime(this.date);
                this.smsSenderBo.update(sender1);
                this.setResponceMsg(PropertyFileReader.getValue("RANK_INFO") + " " + this.smsSenderBo.getSmsSenderRank(sender1.getId()) + PropertyFileReader.getValue("RANK_INFO_MORE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + " " + PropertyFileReader.getValue("RANK_INFO_RETRIC"));
                MchoiceAventuraResponse response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    Help help = new Help();
                    help.setHelpText(sender1.getId().toString());
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.CollectData(sender1, this.getResponceMsg(), this.date);
            }
        } catch (MalformedURLException var6) {
            logger.error(var6);
            var6.printStackTrace();
        } catch (MchoiceAventuraMessagingException var7) {
            logger.error(var7);
            var7.printStackTrace();
        } catch (Exception var8) {
            logger.error(var8);
            var8.printStackTrace();
        }

    }

    private String getResponceMsg() {
        return this.responceMsg;
    }

    private void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
