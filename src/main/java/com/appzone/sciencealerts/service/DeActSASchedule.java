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
import com.appzone.sciencealerts.service.ActSAShedule;
import com.appzone.sciencealerts.service.FinalSms;
import com.appzone.sciencealerts.service.ScienceAlert;
import com.appzone.sciencealerts.service.ScienceAlertsHelp;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class DeActSASchedule {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(ActSAShedule.class);
    FinalSms transaction = new FinalSms();

    public DeActSASchedule() {
    }

    public void SetDActScienceAlertsSchedule(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender ex = this.smsSenderBo.findBySmsSenderAddress(address);
            if(ex != null) {
                if(ex.getIsReg().booleanValue()) {
                    MchoiceAventuraResponse sender;
                    Help helpM;
                    if(ex.getIsSchedularActive().booleanValue()) {
                        ex.setLastActiveTime(date);
                        ex.setIsSchedularActive(Boolean.valueOf(false));
                        this.smsSenderBo.update(ex);
                        this.setResponceMsg(PropertyFileReader.getValue("DEACT_SCHEDULE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_ACT_SCHEDULE"));
                        sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        if(!sender.isSuccess()) {
                            helpM = new Help();
                            helpM.setHelpText(ex.getId().toString());
                            this.helpBo = new HelpBoImpl();
                            this.helpBo.save(helpM);
                        }

                        this.transaction.CollectData(ex, this.getResponceMsg(), date);
                    } else {
                        ex.setIsSchedularActive(Boolean.valueOf(false));
                        ex.setLastActiveTime(date);
                        this.smsSenderBo.update(ex);
                        this.setResponceMsg(PropertyFileReader.getValue("ALREADY_DEACT_SCHEDULE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_ACT_SCHEDULE"));
                        sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        if(!sender.isSuccess()) {
                            helpM = new Help();
                            helpM.setHelpText(ex.getId().toString());
                            this.helpBo = new HelpBoImpl();
                            this.helpBo.save(helpM);
                        }

                        this.transaction.CollectData(ex, this.getResponceMsg(), date);
                    }
                } else {
                    ex.setUserName("UNKNOWN");
                    ex.setIsReg(Boolean.valueOf(true));
                    ex.setIsActive(Boolean.valueOf(true));
                    ex.setMarks(Long.valueOf(Long.parseLong("3")));
                    ex.setIsSchedularActive(Boolean.valueOf(true));
                    ex.setJoinedDate(date);
                    ex.setLastActiveTime(date);
                    this.smsSenderBo.update(ex);
                    ex.setLastActiveTime(date);
                    this.smsSenderBo.update(ex);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                    ScienceAlertsHelp sender1 = new ScienceAlertsHelp();
                    sender1.GetHelp(address, "SCA HELP".split("\\s+"));
                    MchoiceAventuraResponse helpM1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!helpM1.isSuccess()) {
                        Help response = new Help();
                        response.setHelpText(ex.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
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
                this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                ScienceAlertsHelp helpM2 = new ScienceAlertsHelp();
                helpM2.GetHelp(address, "SCA HELP".split("\\s+"));
                MchoiceAventuraResponse response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    Help help = new Help();
                    help.setHelpText(sender2.getId().toString());
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            }
        } catch (MchoiceAventuraMessagingException var8) {
            java.util.logging.Logger.getLogger(ScienceAlert.class.getName()).log(Level.SEVERE, (String)null, var8);
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
