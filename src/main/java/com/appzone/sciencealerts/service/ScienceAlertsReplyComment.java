//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.AppCommentsBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.AppCommentsBoImpl;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsSenderBoImpl;
import com.appzone.sciencealerts.hibernate.model.AppComments;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import com.appzone.sciencealerts.service.FinalSms;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;

public class ScienceAlertsReplyComment {
    private MchoiceAventuraSmsSender smsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo = new SmsSenderBoImpl();
    AppCommentsBo appCommentsBo = new AppCommentsBoImpl();
    private static final Logger logger = Logger.getLogger(ScienceAlertsReplyComment.class);
    FinalSms transaction = new FinalSms();

    public ScienceAlertsReplyComment() {
    }

    public void Reply(String address, String[] replyCmt) {
        Date date = new Date();

        try {
            this.smsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
            if(replyCmt.length > 3) {
                Long e = Long.valueOf(Long.parseLong(replyCmt[2].toString().trim()));
                Long commentId = Long.valueOf(Long.parseLong(replyCmt[3].toString().trim()));
                SmsSender recipient = this.smsSenderBo.findSmsSenderRecordById(Long.valueOf(e.longValue()));
                AppComments appcomment = this.appCommentsBo.findAppCommentsById(Long.valueOf(commentId.longValue()));
                if(recipient != null && recipient.getAddress() != null && appcomment != null && appcomment.getId().longValue() > 0L && !appcomment.getIsChecked().booleanValue()) {
                    if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                        String replyStr = "";

                        for(int finalReply = 4; finalReply < replyCmt.length; ++finalReply) {
                            replyStr = replyStr + " " + replyCmt[finalReply].toString();
                        }

                        replyStr = replyStr.trim();
                        replyStr = replyStr.replace("null", "");
                        String var13 = "Dear " + recipient.getUserName() + ",thank you for using ScienceAlerts." + replyStr;
                        if(var13.length() < 160) {
                            this.setResponceMsg(var13);
                            this.smsSender.sendMessage(this.getResponceMsg(), new String[]{recipient.getAddress()});
                            this.transaction.CollectData(recipient, this.getResponceMsg(), date);
                            appcomment.setIsChecked(Boolean.valueOf(true));
                            this.appCommentsBo.update(appcomment);
                        } else {
                            var13 = var13.substring(0, 159);
                            this.setResponceMsg(var13);
                            this.smsSender.sendMessage(this.getResponceMsg(), new String[]{recipient.getAddress()});
                            this.transaction.CollectData(recipient, this.getResponceMsg(), date);
                            appcomment.setIsChecked(Boolean.valueOf(true));
                            this.appCommentsBo.update(appcomment);
                        }
                    } else {
                        logger.error("Admin Address Doesn\'t Match !!!!  " + date);
                    }
                } else {
                    logger.error("App Comment Reply Failed !!!!  " + date);
                }
            }
        } catch (MalformedURLException var10) {
            logger.error(var10);
            var10.printStackTrace();
        } catch (MchoiceAventuraMessagingException var11) {
            logger.error(var11);
            var11.printStackTrace();
        } catch (Exception var12) {
            logger.error(var12);
            var12.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
