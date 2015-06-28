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
import com.appzone.sciencealerts.service.ScienceAlert;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class ActSAShedule {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(ActSAShedule.class);
    FinalSms transaction = new FinalSms();

    public ActSAShedule() {
    }

    public void SetActScienceAlertsSchedule(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender ex = this.smsSenderBo.findBySmsSenderAddress(address);
            if(ex != null) {
                MchoiceAventuraResponse sender;
                Help response;
                if(ex.getIsReg().booleanValue()) {
                    if(ex.getIsActive().booleanValue()) {
                        System.out.println("SAM " + ex.getIsSchedularActive());
                        if(ex.getIsSchedularActive().booleanValue()) {
                            ex.setLastActiveTime(date);
                            this.smsSenderBo.update(ex);
                            this.setResponceMsg(PropertyFileReader.getValue("ALREADY_ACT_SCHEDULE"));
                            sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                            if(!sender.isSuccess()) {
                                response = new Help();
                                response.setHelpText(ex.getId().toString());
                                this.helpBo = new HelpBoImpl();
                                this.helpBo.save(response);
                            }

                            this.transaction.CollectData(ex, this.getResponceMsg(), date);
                        } else {
                            ex.setIsSchedularActive(Boolean.valueOf(true));
                            ex.setLastActiveTime(date);
                            this.smsSenderBo.update(ex);
                            this.setResponceMsg(PropertyFileReader.getValue("ACT_SCHEDULE"));
                            sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                            if(!sender.isSuccess()) {
                                response = new Help();
                                response.setHelpText(ex.getId().toString());
                                this.helpBo = new HelpBoImpl();
                                this.helpBo.save(response);
                            }

                            this.transaction.CollectData(ex, this.getResponceMsg(), date);
                        }
                    } else {
                        ex.setLastActiveTime(date);
                        this.smsSenderBo.update(ex);
                        this.setResponceMsg(PropertyFileReader.getValue("NOT_AN_ACTIVE_ACCOUNT") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + ".");
                        sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        if(!sender.isSuccess()) {
                            response = new Help();
                            response.setHelpText(ex.getId().toString());
                            this.helpBo = new HelpBoImpl();
                            this.helpBo.save(response);
                        }

                        this.transaction.CollectData(ex, this.getResponceMsg(), date);
                    }
                } else {
                    ex.setUserName("UNKNOWN");
                    ex.setIsReg(Boolean.valueOf(true));
                    ex.setIsActive(Boolean.valueOf(true));
                    ex.setMarks(Long.valueOf(Long.parseLong("2")));
                    ex.setIsSchedularActive(Boolean.valueOf(true));
                    ex.setJoinedDate(date);
                    ex.setLastActiveTime(date);
                    this.smsSenderBo.update(ex);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                    sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender.isSuccess()) {
                        response = new Help();
                        response.setHelpText(ex.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    this.transaction.CollectData(ex, this.getResponceMsg(), date);
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
                this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                MchoiceAventuraResponse response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    Help help = new Help();
                    help.setHelpText(sender1.getId().toString());
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
}
