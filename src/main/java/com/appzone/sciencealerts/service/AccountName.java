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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class AccountName {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    HelpBo helpBo;
    private static final Logger logger = Logger.getLogger(AccountName.class);
    FinalSms transaction = new FinalSms();

    public AccountName() {
    }

    public void AccountUserName(String address, String[] setUsrNameStr) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            Help help;
            SmsSender var18;
            MchoiceAventuraResponse var20;
            if(setUsrNameStr.length < 8) {
                if(e != null) {
                    String sender = "";

                    for(int response = 2; response < setUsrNameStr.length; ++response) {
                        sender = sender + " " + setUsrNameStr[response].trim();
                    }

                    sender = sender.trim();
                    if(sender.length() > 2 && sender.length() < 20) {
                        boolean var21 = this.smsSenderBo.checkIsSmsSenderExists(sender.trim());
                        Help help1;
                        MchoiceAventuraResponse var22;
                        if(e.getIsReg().booleanValue()) {
                            if(e.getIsActive().booleanValue()) {
                                if(var21) {
                                    if(e.getUserName().equalsIgnoreCase(sender.trim())) {
                                        e.setLastActiveTime(date);
                                        this.smsSenderBo.update(e);
                                        this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_ALREADY_EXISTS_CHANGE_FOR_SAME_USER_NAME_OLD_MEMEBER") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_SETN_DIFF1"));
                                        var22 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                                        if(!var22.isSuccess()) {
                                            help1 = new Help();
                                            help1.setHelpText(e.getId().toString());
                                            this.helpBo = new HelpBoImpl();
                                            this.helpBo.save(help1);
                                        }

                                        this.transaction.CollectData(e, this.getResponceMsg(), date);
                                    } else {
                                        e.setLastActiveTime(date);
                                        this.smsSenderBo.update(e);
                                        this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_ALREADY_EXISTS_ERROR_OLD_MEMBER") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_SETN_DIFF2"));
                                        var22 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                                        if(!var22.isSuccess()) {
                                            help1 = new Help();
                                            help1.setHelpText(e.getId().toString());
                                            this.helpBo = new HelpBoImpl();
                                            this.helpBo.save(help1);
                                        }

                                        this.transaction.CollectData(e, this.getResponceMsg(), date);
                                    }
                                } else {
                                    e.setLastActiveTime(date);
                                    e.setUserName(sender.trim());
                                    this.smsSenderBo.update(e);
                                    this.setResponceMsg(PropertyFileReader.getValue("EXISTING_USER_NAME_CHANGE_SUCCESS_SUB1") + sender.trim() + PropertyFileReader.getValue("EXISTING_USER_NAME_CHANGE_SUCCESS_SUB2") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_GET"));
                                    var22 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                                    if(!var22.isSuccess()) {
                                        help1 = new Help();
                                        help1.setHelpText(e.getId().toString());
                                        this.helpBo = new HelpBoImpl();
                                        this.helpBo.save(help1);
                                    }

                                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                                }
                            } else if(var21) {
                                e.setLastActiveTime(date);
                                this.smsSenderBo.update(e);
                                this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_ALREADY_EXISTS_ERROR_NEW_MEMBER") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_SETN"));
                                var22 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                                if(!var22.isSuccess()) {
                                    help1 = new Help();
                                    help1.setHelpText(e.getId().toString());
                                    this.helpBo = new HelpBoImpl();
                                    this.helpBo.save(help1);
                                }

                                this.transaction.CollectData(e, this.getResponceMsg(), date);
                            } else {
                                Long var24 = e.getMarks();
                                var24 = Long.valueOf(var24.longValue() + 1L);
                                e.setMarks(var24);
                                e.setLastActiveTime(date);
                                e.setUserName(sender.trim());
                                e.setIsActive(Boolean.valueOf(true));
                                e.setIsSchedularActive(Boolean.valueOf(true));
                                this.smsSenderBo.update(e);
                                this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_SET_SUCCESS_SUB1") + sender.trim() + PropertyFileReader.getValue("USER_NAME_SET_SUCCESS_SUB2") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_GET"));
                                MchoiceAventuraResponse var25 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                                if(!var25.isSuccess()) {
                                    Help smsSenderScienceAlertFirst = new Help();
                                    smsSenderScienceAlertFirst.setHelpText(e.getId().toString());
                                    this.helpBo = new HelpBoImpl();
                                    this.helpBo.save(smsSenderScienceAlertFirst);
                                }

                                this.transaction.CollectData(e, this.getResponceMsg(), date);
                                MchoiceAventuraSmsSender var26 = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                FinalSms transactionFirst = new FinalSms();
                                ScienceAlert scienceAlert = new ScienceAlert();
                                String alert = scienceAlert.GetScienceAlert().getSms();
                                this.setResponceMsg(alert);
                                MchoiceAventuraResponse response_ = var26.sendMessage(this.getResponceMsg(), new String[]{address});
                                if(!response_.isSuccess()) {
                                    Help help2 = new Help();
                                    help2.setHelpText(e.getId().toString());
                                    this.helpBo = new HelpBoImpl();
                                    this.helpBo.save(help2);
                                }

                                transactionFirst.CollectData(e, this.getResponceMsg(), date);
                            }
                        } else {
                            e.setLastActiveTime(date);
                            this.smsSenderBo.update(e);
                            this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                            var22 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                            if(!var22.isSuccess()) {
                                help1 = new Help();
                                help1.setHelpText(e.getId().toString());
                                this.helpBo = new HelpBoImpl();
                                this.helpBo.save(help1);
                            }

                            this.transaction.CollectData(e, this.getResponceMsg(), date);
                        }
                    } else {
                        e.setLastActiveTime(date);
                        this.smsSenderBo.update(e);
                        this.setResponceMsg(PropertyFileReader.getValue("USER_NAME_LENGTH_ERROR") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_SETN"));
                        var20 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        if(!var20.isSuccess()) {
                            help = new Help();
                            help.setHelpText(e.getId().toString());
                            this.helpBo = new HelpBoImpl();
                            this.helpBo.save(help);
                        }

                        this.transaction.CollectData(e, this.getResponceMsg(), date);
                    }
                } else {
                    var18 = new SmsSender();
                    var18.setAddress(address.toString());
                    var18.setUserName("UNKNOWN");
                    var18.setIsReg(Boolean.valueOf(true));
                    var18.setIsActive(Boolean.valueOf(true));
                    var18.setMarks(Long.valueOf(Long.parseLong("2")));
                    var18.setIsSchedularActive(Boolean.valueOf(true));
                    var18.setJoinedDate(date);
                    var18.setLastActiveTime(date);
                    this.smsSenderBo.save(var18);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                    var20 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!var20.isSuccess()) {
                        help = new Help();
                        help.setHelpText(var18.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(help);
                    }

                    this.transaction.CollectData(var18, this.getResponceMsg(), date);
                }
            } else if(e != null) {
                MchoiceAventuraResponse var19;
                Help var23;
                if(e.getIsReg().booleanValue()) {
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("INCORRECT_COMMAND") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP"));
                    var19 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!var19.isSuccess()) {
                        var23 = new Help();
                        var23.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(var23);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                } else {
                    e.setLastActiveTime(date);
                    this.smsSenderBo.update(e);
                    this.setResponceMsg(PropertyFileReader.getValue("REG_ERROR"));
                    var19 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!var19.isSuccess()) {
                        var23 = new Help();
                        var23.setHelpText(e.getId().toString());
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(var23);
                    }

                    this.transaction.CollectData(e, this.getResponceMsg(), date);
                }
            } else {
                var18 = new SmsSender();
                var18.setAddress(address.toString());
                var18.setUserName("UNKNOWN");
                var18.setIsReg(Boolean.valueOf(true));
                var18.setIsActive(Boolean.valueOf(true));
                var18.setMarks(Long.valueOf(Long.parseLong("2")));
                var18.setIsSchedularActive(Boolean.valueOf(true));
                var18.setJoinedDate(date);
                var18.setLastActiveTime(date);
                this.smsSenderBo.save(var18);
                this.setResponceMsg(PropertyFileReader.getValue("INCORRECT_COMMAND") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_HELP"));
                var20 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                if(!var20.isSuccess()) {
                    help = new Help();
                    help.setHelpText(var18.getId().toString());
                    this.helpBo = new HelpBoImpl();
                    this.helpBo.save(help);
                }

                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            }
        } catch (MalformedURLException var15) {
            logger.error(var15);
            var15.printStackTrace();
        } catch (MchoiceAventuraMessagingException var16) {
            logger.error(var16);
            var16.printStackTrace();
        } catch (Exception var17) {
            logger.error(var17);
            var17.printStackTrace();
        }

    }

    private String getResponceMsg() {
        return this.responceMsg;
    }

    private void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
