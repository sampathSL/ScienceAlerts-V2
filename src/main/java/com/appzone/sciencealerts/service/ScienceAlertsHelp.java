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
import com.appzone.sciencealerts.service.InvalidMessage;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraResponse;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class ScienceAlertsHelp {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(ScienceAlertsHelp.class);
    FinalSms transaction = new FinalSms();

    public ScienceAlertsHelp() {
    }

    public void GetHelp(String address, String[] helpText) {
        Date date = new Date();

        try {
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            SmsSender response;
            if(e == null) {
                response = new SmsSender();
                response.setAddress(address.toString());
                response.setUserName("UNKNOWN");
                response.setIsReg(Boolean.valueOf(true));
                response.setIsActive(Boolean.valueOf(true));
                response.setMarks(Long.valueOf(Long.parseLong("3")));
                response.setIsSchedularActive(Boolean.valueOf(true));
                response.setJoinedDate(date);
                response.setLastActiveTime(date);
                this.smsSenderBo.save(response);
            }

            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            Help invalidMessage;
            MchoiceAventuraResponse response1;
            if(helpText.length > 3) {
                this.setResponceMsg(PropertyFileReader.getValue("INCORRECT_COMMAND") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP"));
                response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    invalidMessage = new Help();
                    invalidMessage.setHelpText(address);
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(invalidMessage);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            } else if(helpText.length == 2) {
                this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTIONS") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP1"));
                response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!response1.isSuccess()) {
                    invalidMessage = new Help();
                    invalidMessage.setHelpText(address);
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(invalidMessage);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            } else {
                response = null;
                if(helpText[2].trim().toString().toLowerCase().equalsIgnoreCase("")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTIONS") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP1"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().toLowerCase().equalsIgnoreCase(" ")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTIONS") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP1"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("1")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION1") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_GET"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("2")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION2") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_SETN"));
                    this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("3")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION3") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_GETINFO"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("4")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION4") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_ACT_SCHEDULE"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("5")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION5") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_DEACT_SCHEDULE"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("6")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION6") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_RANK"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("7")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION7") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_COMMENT"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else if(helpText[2].trim().toString().equalsIgnoreCase("8")) {
                    this.setResponceMsg(PropertyFileReader.getValue("GET_HELP_OPTION8") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_UNREG"));
                    response1 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!response1.isSuccess()) {
                        invalidMessage = new Help();
                        invalidMessage.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(invalidMessage);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                } else {
                    InvalidMessage invalidMessage1 = new InvalidMessage();
                    invalidMessage1.InformInvalideMessage(address);
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
