//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.bo.SmsSenderBo;
import com.appzone.sciencealerts.hibernate.bo.impl.ScienceAlertsBoImpl;
import com.appzone.sciencealerts.hibernate.dao.impl.SmsSenderDaoImpl;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.properties.AppZoneConfig;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraMessagingException;
import hsenidmobile.sdp.rest.servletbase.MchoiceAventuraSmsSender;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class ScienceAlertsAdvert2 {
    private MchoiceAventuraSmsSender dailySmsSender;
    private String responceMsg = "";
    SmsSenderBo smsSenderBo;
    ScienceAlertsBo scienceAlertsBo;
    private static final Logger logger = Logger.getLogger(ScienceAlertsAdvert2.class);

    public ScienceAlertsAdvert2() {
    }

    public void SendAdvert2(String address, String[] scaAdd) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            ScienceAlerts e;
            if(scaAdd.length == 3) {
                if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                    try {
                        if(scaAdd[2] != null) {
                            this.scienceAlertsBo = new ScienceAlertsBoImpl();
                            e = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[2].toString())));
                            if(e.getSms() != null) {
                                SmsSenderDaoImpl scienceAlerts2 = new SmsSenderDaoImpl();
                                List scienceAlerts3 = scienceAlerts2.getDailyAlertsSmsSenders();
                                if(scienceAlerts3 != null) {
                                    if(scienceAlerts3.size() > 0) {
                                        try {
                                            logger.info("senderList.size() is ========== " + scienceAlerts3.size());
                                            this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                            this.setResponceMsg(e.getSms().toString());
                                            this.dailySmsSender.broadcastMessage(this.getResponceMsg());
                                            logger.info("Daily SMS Sending Success AT ========== " + dateFormat.format(date) + " Number of smses Sent = " + scienceAlerts3.size());
                                            e.setScheduled(Boolean.valueOf(true));
                                            this.scienceAlertsBo.update(e);
                                        } catch (MchoiceAventuraMessagingException var16) {
                                            logger.error(var16);
                                            var16.printStackTrace();
                                        } catch (Exception var17) {
                                            logger.error(var17);
                                            var17.printStackTrace();
                                        }
                                    } else {
                                        logger.info("dailyList.size() is 0");
                                    }
                                } else {
                                    logger.info("dailyList.size() is null");
                                }
                            } else {
                                logger.info("Science Alerts SMS is null");
                            }
                        } else {
                            logger.info("Science alerts Id is null");
                        }
                    } catch (Exception var18) {
                        logger.error(var18);
                        var18.printStackTrace();
                    }
                } else {
                    logger.error("Address doesn\'t match ========Security Alert!!!=======" + date);
                }
            } else {
                ScienceAlerts var24;
                if(scaAdd.length == 4) {
                    if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                        try {
                            if(scaAdd[2] != null && scaAdd[3] != null) {
                                this.scienceAlertsBo = new ScienceAlertsBoImpl();
                                e = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[2].toString())));
                                var24 = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[3].toString())));
                                if(e.getSms() != null && var24.getSms() != null) {
                                    SmsSenderDaoImpl var25 = new SmsSenderDaoImpl();
                                    List smsSenderDaoImpl = var25.getDailyAlertsSmsSenders();
                                    if(smsSenderDaoImpl != null) {
                                        if(smsSenderDaoImpl.size() > 0) {
                                            try {
                                                logger.info("senderList.size() is ========== " + smsSenderDaoImpl.size());

                                                for(int dailyList = 0; dailyList < smsSenderDaoImpl.size(); ++dailyList) {
                                                    try {
                                                        String ex = ((SmsSender)smsSenderDaoImpl.get(dailyList)).getAddress();
                                                        this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                        this.setResponceMsg(e.getSms().toString().trim());
                                                        this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{ex});
                                                        this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                        this.setResponceMsg(var24.getSms().toString().trim());
                                                        this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{ex});
                                                    } catch (MchoiceAventuraMessagingException var14) {
                                                        logger.error(var14);
                                                    } catch (Exception var15) {
                                                        logger.error(var15);
                                                        var15.printStackTrace();
                                                    }
                                                }

                                                logger.info("Daily SMS Sending Success AT ========== " + dateFormat.format(date) + " Number of smses Sent = " + smsSenderDaoImpl.size() * 2);
                                                e.setScheduled(Boolean.valueOf(true));
                                                this.scienceAlertsBo.update(e);
                                                var24.setScheduled(Boolean.valueOf(true));
                                                this.scienceAlertsBo.update(var24);
                                            } catch (Exception var21) {
                                                logger.error(var21);
                                                var21.printStackTrace();
                                            }
                                        } else {
                                            logger.info("dailyList.size() is 0");
                                        }
                                    } else {
                                        logger.info("dailyList.size() is null");
                                    }
                                } else {
                                    logger.info("Science Alerts SMS is null");
                                }
                            } else {
                                logger.info("Science alerts Id is null");
                            }
                        } catch (Exception var22) {
                            logger.error(var22);
                            var22.printStackTrace();
                        }
                    } else {
                        logger.error("Address doesn\'t match ========Security Alert!!!=======" + date);
                    }
                } else if(scaAdd.length == 5) {
                    if(address.equalsIgnoreCase(PropertyFileReader.getValue("MY_ADDRESS"))) {
                        try {
                            if(scaAdd[2] != null && scaAdd[3] != null && scaAdd[4] != null) {
                                this.scienceAlertsBo = new ScienceAlertsBoImpl();
                                e = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[2].toString())));
                                var24 = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[3].toString())));
                                ScienceAlerts var26 = this.scienceAlertsBo.getAdminScienceAlert(Long.valueOf(Long.parseLong(scaAdd[4].toString())));
                                if(e.getSms() != null && var24.getSms() != null && var26.getSms() != null) {
                                    SmsSenderDaoImpl var27 = new SmsSenderDaoImpl();
                                    List var28 = var27.getDailyAlertsSmsSenders();
                                    if(var28 != null) {
                                        if(var28.size() > 0) {
                                            try {
                                                logger.info("senderList.size() is ========== " + var28.size());

                                                for(int var29 = 0; var29 < var28.size(); ++var29) {
                                                    try {
                                                        String ex1 = ((SmsSender)var28.get(var29)).getAddress();
                                                        this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                        this.setResponceMsg(e.getSms().toString().trim());
                                                        this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{ex1});
                                                        this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                        this.setResponceMsg(var24.getSms().toString().trim());
                                                        this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{ex1});
                                                        this.dailySmsSender = new MchoiceAventuraSmsSender(new URL(AppZoneConfig.getURL()), AppZoneConfig.getApp_Id(), AppZoneConfig.getPass());
                                                        this.setResponceMsg(var26.getSms().toString().trim());
                                                        this.dailySmsSender.sendMessage(this.getResponceMsg(), new String[]{ex1});
                                                    } catch (MchoiceAventuraMessagingException var12) {
                                                        logger.error(var12);
                                                    } catch (Exception var13) {
                                                        logger.error(var13);
                                                        var13.printStackTrace();
                                                    }
                                                }

                                                logger.info("Daily SMS Sending Success AT ========== " + dateFormat.format(date) + " Number of smses Sent = " + var28.size() * 3);
                                                e.setScheduled(Boolean.valueOf(true));
                                                this.scienceAlertsBo.update(e);
                                                var24.setScheduled(Boolean.valueOf(true));
                                                this.scienceAlertsBo.update(var24);
                                                var26.setScheduled(Boolean.valueOf(true));
                                                this.scienceAlertsBo.update(var26);
                                            } catch (Exception var19) {
                                                logger.error(var19);
                                                var19.printStackTrace();
                                            }
                                        } else {
                                            logger.info("dailyList.size() is 0");
                                        }
                                    } else {
                                        logger.info("dailyList.size() is null");
                                    }
                                } else {
                                    logger.info("Science Alerts SMS is null");
                                }
                            } else {
                                logger.info("Science alerts Id is null");
                            }
                        } catch (Exception var20) {
                            logger.error(var20);
                            var20.printStackTrace();
                        }
                    } else {
                        logger.error("Address doesn\'t match ========Security Alert!!!=======" + date);
                    }
                } else {
                    logger.error("Parameters missing or invalide parameter range" + date);
                }
            }
        } catch (Exception var23) {
            logger.error(var23);
            var23.printStackTrace();
        }

    }

    public String getResponceMsg() {
        return this.responceMsg;
    }

    public void setResponceMsg(String responceMsg) {
        this.responceMsg = responceMsg;
    }
}
