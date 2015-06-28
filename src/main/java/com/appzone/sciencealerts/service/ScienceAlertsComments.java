//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.AppCommentsBo;
import com.appzone.sciencealerts.hibernate.bo.HelpBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.AppCommentsBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.HelpBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.AppComments;
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

public class ScienceAlertsComments {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    AppCommentsBo appCommentsBo = new AppCommentsBoImpl();
    HelpBo helpBo = new HelpBoImpl();
    private static final Logger logger = Logger.getLogger(ScienceAlertsComments.class);
    FinalSms transaction = new FinalSms();

    public ScienceAlertsComments() {
    }

    public void SendAppComment(String address, String[] setAppComment) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            SmsSender e = this.smsSenderBo.findBySmsSenderAddress(address);
            SmsSender smsSenderAppCommentsSender;
            if(e == null) {
                smsSenderAppCommentsSender = new SmsSender();
                smsSenderAppCommentsSender.setAddress(address.toString());
                smsSenderAppCommentsSender.setUserName("UNKNOWN");
                smsSenderAppCommentsSender.setIsReg(Boolean.valueOf(true));
                smsSenderAppCommentsSender.setIsActive(Boolean.valueOf(true));
                smsSenderAppCommentsSender.setMarks(Long.valueOf(Long.parseLong("3")));
                smsSenderAppCommentsSender.setIsSchedularActive(Boolean.valueOf(true));
                smsSenderAppCommentsSender.setJoinedDate(date);
                smsSenderAppCommentsSender.setLastActiveTime(date);
                this.smsSenderBo.save(smsSenderAppCommentsSender);
            }

            if(setAppComment.length <= 2) {
                this.setResponceMsg(PropertyFileReader.getValue("SEND_A_COMMENTS_NO_MESSAGE") + " " + PropertyFileReader.getValue("DEFAULT_PORT") + "." + PropertyFileReader.getValue("EXAMPLE_COMMENT"));
                this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                this.transaction.NotAMember(address, this.getResponceMsg(), date);
            } else {
                smsSenderAppCommentsSender = this.smsSenderBo.findBySmsSenderAddress(address);
                String comment = "";

                for(int appComments = 2; appComments < setAppComment.length; ++appComments) {
                    comment = comment + " " + setAppComment[appComments].trim().toString();
                }

                comment = comment.trim();
                AppComments var17;
                MchoiceAventuraResponse var18;
                Help var19;
                Long var20;
                Long var21;
                String var22;
                if(smsSenderAppCommentsSender != null) {
                    if(smsSenderAppCommentsSender.getIsReg().booleanValue()) {
                        if(smsSenderAppCommentsSender.getIsActive().booleanValue()) {
                            Long var16 = smsSenderAppCommentsSender.getMarks();
                            var16 = Long.valueOf(var16.longValue() + 1L);
                            smsSenderAppCommentsSender.setMarks(var16);
                            smsSenderAppCommentsSender.setLastActiveTime(date);
                            this.smsSenderBo.update(smsSenderAppCommentsSender);
                            comment = comment.replace("null", "");
                            AppComments response = new AppComments();
                            response.setSmssender(smsSenderAppCommentsSender);
                            response.setIsChecked(Boolean.valueOf(false));
                            response.setComment(comment);
                            this.appCommentsBo.save(response);
                            this.setResponceMsg(PropertyFileReader.getValue("SEND_A_COMMENTS_THANK_YOU"));
                            MchoiceAventuraResponse senderId = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                            if(!senderId.isSuccess()) {
                                Help commentId = new Help();
                                commentId.setHelpText(smsSenderAppCommentsSender.getId().toString());
                                this.helpBo = new HelpBoImpl();
                                this.helpBo.save(commentId);
                            }

                            this.transaction.CollectData(smsSenderAppCommentsSender, this.getResponceMsg(), date);
                            var21 = smsSenderAppCommentsSender.getId();
                            Long finalMsg = response.getId();
                            String finalMsg1 = var21.toString() + "-" + finalMsg.toString() + "-" + comment;
                            if(finalMsg1.length() < 160) {
                                this.smsSender.sendMessage(finalMsg1, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                            } else {
                                finalMsg1 = finalMsg1.substring(0, 159);
                                this.smsSender.sendMessage(finalMsg1, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                            }
                        } else {
                            comment = comment.replace("null", "");
                            var17 = new AppComments();
                            var17.setSmssender(smsSenderAppCommentsSender);
                            var17.setIsChecked(Boolean.valueOf(false));
                            var17.setComment(comment);
                            this.appCommentsBo.save(var17);
                            this.setResponceMsg(PropertyFileReader.getValue("SEND_A_COMMENTS_THANK_YOU"));
                            var18 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                            if(!var18.isSuccess()) {
                                var19 = new Help();
                                var19.setHelpText(smsSenderAppCommentsSender.getId().toString());
                                this.helpBo = new HelpBoImpl();
                                this.helpBo.save(var19);
                            }

                            this.transaction.NotAMember(address, this.getResponceMsg(), date);
                            var20 = smsSenderAppCommentsSender.getId();
                            var21 = var17.getId();
                            var22 = var20.toString() + "-" + var21.toString() + "-" + comment;
                            if(var22.length() < 160) {
                                this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                            } else {
                                var22 = var22.substring(0, 159);
                                this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                            }
                        }
                    } else {
                        comment = comment.replace("null", "");
                        var17 = new AppComments();
                        var17.setSmssender(smsSenderAppCommentsSender);
                        var17.setIsChecked(Boolean.valueOf(false));
                        var17.setComment(comment);
                        this.appCommentsBo.save(var17);
                        this.setResponceMsg(PropertyFileReader.getValue("SEND_A_COMMENTS_THANK_YOU"));
                        var18 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                        if(!var18.isSuccess()) {
                            var19 = new Help();
                            var19.setHelpText(address);
                            this.helpBo = new HelpBoImpl();
                            this.helpBo.save(var19);
                        }

                        this.transaction.NotAMember(address, this.getResponceMsg(), date);
                        var20 = smsSenderAppCommentsSender.getId();
                        var21 = var17.getId();
                        var22 = var20.toString() + "-" + var21.toString() + "-" + comment;
                        if(var22.length() < 160) {
                            this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                        } else {
                            var22 = var22.substring(0, 159);
                            this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                        }
                    }
                } else {
                    comment = comment.replace("null", "");
                    var17 = new AppComments();
                    var17.setSmssender(smsSenderAppCommentsSender);
                    var17.setIsChecked(Boolean.valueOf(false));
                    var17.setComment(comment);
                    this.appCommentsBo.save(var17);
                    this.setResponceMsg(PropertyFileReader.getValue("SEND_A_COMMENTS_THANK_YOU"));
                    var18 = this.smsSender.sendMessage(this.getResponceMsg(), new String[]{address});
                    if(!var18.isSuccess()) {
                        var19 = new Help();
                        var19.setHelpText(address);
                        this.helpBo = new HelpBoImpl();
                        this.helpBo.save(var19);
                    }

                    this.transaction.NotAMember(address, this.getResponceMsg(), date);
                    var20 = Long.valueOf(Long.parseLong("0"));
                    var21 = var17.getId();
                    var22 = var20.toString() + "-" + var21.toString() + "-" + comment;
                    if(var22.length() < 160) {
                        this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                    } else {
                        var22 = var22.substring(0, 159);
                        this.smsSender.sendMessage(var22, new String[]{PropertyFileReader.getValue("MY_ADDRESS")});
                    }
                }
            }
        } catch (MalformedURLException var13) {
            logger.error(var13);
            var13.printStackTrace();
        } catch (MchoiceAventuraMessagingException var14) {
            logger.error(var14);
            var14.printStackTrace();
        } catch (Exception var15) {
            logger.error(var15);
            var15.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }

    private void writeData(SmsSender smsSender) {
        logger.info(smsSender.getId() + "=============Sms Sender Id===============");
        logger.info(smsSender.getAddress() + "=============Sms Sender Address===============");
        logger.info(smsSender.getUserName() + "=============Sms Sender UserName===============");
        logger.info(smsSender.getMarks() + "=============Sms Sender Marks===============");
        logger.info(smsSender.getJoinedDate() + "=============Sms Sender JoinedDates===============");
        logger.info(smsSender.getIsActive() + "=============Sms Sender IsActive===============");
    }
}
