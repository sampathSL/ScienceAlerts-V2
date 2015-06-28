//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.HelpBo;
import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.HelpBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.ScienceAlertsBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.Help;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.service.FinalSms;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class ScienceAlert {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    ScienceAlertsBo scienceAlertsBo = new ScienceAlertsBoImpl();
    private static final Logger logger = Logger.getLogger(ScienceAlert.class);
    FinalSms transaction = new FinalSms();

    public ScienceAlert() {
    }

    public void SendScienceAlert(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender ex = this.smsSenderBo.findBySmsSenderAddress(address);
            MchoiceAventuraResponse response;
            Help help;
            if(ex != null) {
                if(ex.getIsReg().booleanValue()) {
                    Long sender = ex.getMarks();
                    sender = Long.valueOf(sender.longValue() + 1L);
                    ex.setMarks(sender);
                    ex.setLastActiveTime(date);
                    this.smsSenderBo.update(ex);
                    this.setResponceMsg(this.GetScienceAlert().getSms());
                    response = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response.isSuccess()) {
                        help = new Help();
                        help.setHelpText(ex.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(help);
                    }

                    this.transaction.CollectData(ex, this.getResponceMsg(), date);
                } else {
                    ex.setAddress(address.toString());
                    ex.setUserName("UNKNOWN");
                    ex.setIsReg(Boolean.valueOf(true));
                    ex.setIsActive(Boolean.valueOf(true));
                    ex.setMarks(Long.valueOf(Long.parseLong("3")));
                    ex.setIsSchedularActive(Boolean.valueOf(true));
                    ex.setJoinedDate(date);
                    ex.setLastActiveTime(date);
                    this.smsSenderBo.update(ex);
                    this.setResponceMsg(this.GetScienceAlert().getSms());
                    MchoiceAventuraResponse sender1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender1.isSuccess()) {
                        Help response1 = new Help();
                        response1.setHelpText(ex.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response1);
                    }

                    this.transaction.CollectData(ex, this.getResponceMsg(), date);
                }
            } else {
                SmsSender sender2 = new SmsSender();
                sender2.setAddress(address.toString());
                sender2.setUserName("UNKNOWN");
                sender2.setIsReg(Boolean.valueOf(true));
                sender2.setIsActive(Boolean.valueOf(true));
                sender2.setMarks(Long.valueOf(Long.parseLong("3")));
                sender2.setIsSchedularActive(Boolean.valueOf(true));
                sender2.setJoinedDate(date);
                sender2.setLastActiveTime(date);
                this.smsSenderBo.save(sender2);
                this.setResponceMsg(this.GetScienceAlert().getSms());
                response = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response.isSuccess()) {
                    help = new Help();
                    help.setHelpText(sender2.getId().toString());
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            }
        } catch (MchoiceAventuraMessagingException var7) {
            java.util.logging.Logger.getLogger(ScienceAlert.class.getName()).log(Level.SEVERE, (String)null, var7);
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

    public ScienceAlerts GetScienceAlert() {
        return this.scienceAlertsBo.getRandomScienceAlert();
    }
}
