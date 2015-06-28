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
import java.util.Date;
import org.apache.log4j.Logger;

public class InvalidMessage {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(InvalidMessage.class);
    FinalSms transaction = new FinalSms();

    public InvalidMessage() {
    }

    public void InformInvalideMessage(String address) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            if(e != null) {
                e.setLastActiveTime(date);
                this.smsSenderBo.update(e);
                this.setResponceMsg(PropertyFileReader.getValue("INCORRECT_COMMAND") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP"));
                MchoiceAventuraResponse sender = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!sender.isSuccess()) {
                    Help response = new Help();
                    response.setHelpText(address);
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(response);
                }

                this.transaction.CollectData(e, this.getResponceMsg(), date);
            } else {
                SmsSender sender1 = new SmsSender();
                sender1.setAddress(address.toString());
                sender1.setUserName("UNKNOWN");
                sender1.setIsReg(Boolean.valueOf(true));
                sender1.setIsActive(Boolean.valueOf(true));
                sender1.setMarks(Long.valueOf(Long.parseLong("2")));
                sender1.setIsSchedularActive(Boolean.valueOf(false));
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
