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
import com.appzone.sciencealerts.service.ScienceAlertsHelp;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class RegUser {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(RegUser.class);
    FinalSms transaction = new FinalSms();

    public RegUser() {
    }

    public void ScienceAlertReg(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            if(e != null) {
                MchoiceAventuraResponse sender;
                Help response;
                ScienceAlertsHelp response1;
                if(e.getIsReg().booleanValue()) {
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ALREADY") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + ".");
                    sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    response1 = new ScienceAlertsHelp();
                    response1.GetHelp(address, "SCA HELP".split("\\s+"));
                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                } else {
                    e.setIsReg(Boolean.valueOf(true));
                    e.setIsActive(Boolean.valueOf(true));
                    e.setIsSchedularActive(Boolean.valueOf(true));
                    e.setJoinedDate(date);
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_SUCCESS_PART1_ACT1") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + ".");
                    sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!sender.isSuccess()) {
                        response = new Help();
                        response.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(response);
                    }

                    response1 = new ScienceAlertsHelp();
                    response1.GetHelp(address, "SCA HELP".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 1".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 2".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 3".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 4".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 5".split("\\s+"));
                    response1.GetHelp(address, "SCA HELP 7".split("\\s+"));
                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                }
            } else {
                SmsSender sender1 = new SmsSender();
                sender1.setAddress(address.toString());
                sender1.setUserName("UNKNOWN");
                sender1.setIsReg(Boolean.valueOf(true));
                sender1.setIsActive(Boolean.valueOf(true));
                sender1.setMarks(Long.valueOf(Long.parseLong("3")));
                sender1.setIsSchedularActive(Boolean.valueOf(true));
                sender1.setJoinedDate(date);
                sender1.setLastActiveTime(date);
                this.smsSenderBo.save(sender1);
                Help help;
                MchoiceAventuraResponse response2;
                if(sender1.getId().longValue() != 0L) {
                    this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_SET_SUCCESS_SUB3") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_GET"));
                    response2 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response2.isSuccess()) {
                        help = new Help();
                        help.setHelpText(sender1.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(help);
                    }

                    ScienceAlertsHelp help1 = new ScienceAlertsHelp();
                    help1.GetHelp(address, "SCA HELP".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 1".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 2".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 3".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 4".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 5".split("\\s+"));
                    help1.GetHelp(address, "SCA HELP 7".split("\\s+"));
                    this.transaction.CollectData(sender1, this.getResponceMsg(), date);
                } else {
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                    response2 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response2.isSuccess()) {
                        help = new Help();
                        help.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(help);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
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

    private String getResponceMsg() {
        return this.responceMsg;
    }

    private void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
