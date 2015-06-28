//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.scheduletasks;

import com.appzone.sciencealerts.datafeeds.Feed;
import com.appzone.sciencealerts.datafeeds.FeedMessage;
import com.appzone.sciencealerts.datafeeds.RSSFeedParser;
import com.appzone.sciencealerts.hibernate.bo.ScienceAlertsBo;
import com.appzone.sciencealerts.hibernate.bo.impl.ScienceAlertsBoImpl;
import com.appzone.sciencealerts.hibernate.model.ScienceAlerts;
import com.appzone.sciencealerts.properties.PropertyFileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class DataFeedScheduleTask {
    private static final Logger logger = Logger.getLogger(DataFeedScheduleTask.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ScienceAlertsBo scienceAlertsBo;

    public DataFeedScheduleTask() {
    }

    public void FindAlerts(String url) {
        Calendar oldDay = Calendar.getInstance();
        oldDay.add(5, Integer.parseInt(PropertyFileReader.getValue("FEEDS_SET_DATE")));
        String checkDate = this.dateFormat.format(oldDay.getTime());
        logger.info("===========================");
        logger.info("Starting - " + checkDate + "-" + url);
        logger.info("===========================");

        try {
            RSSFeedParser ex = new RSSFeedParser(url);
            Feed feed = ex.readFeed();
            Iterator nextItem = feed.getEntries().iterator();

            while(nextItem.hasNext()) {
                FeedMessage message = (FeedMessage)nextItem.next();
                if(!message.getTitle().toLowerCase().contains("video") && !message.getTitle().toLowerCase().contains("picture") && !message.getTitle().toLowerCase().contains("gotta-see video") && !message.getTitle().toLowerCase().contains("in picture") && !message.getTitle().toLowerCase().contains("sex") && !message.getTitle().toLowerCase().contains("punk") && !message.getTitle().toLowerCase().contains("zombie") && !message.getTitle().toLowerCase().contains("zombies") && !message.getTitle().toLowerCase().contains("killer") && !message.getTitle().toLowerCase().contains("podcast:") && !message.getTitle().toLowerCase().contains("ass") && message.getPubDate() != null && oldDay.getTime().before(new Date(message.getPubDate()))) {
                    ScienceAlerts scienceAlerts = new ScienceAlerts();
                    this.scienceAlertsBo = new ScienceAlertsBoImpl();
                    if(!this.scienceAlertsBo.checkIsScienceAlertExists(message.getTitle().replaceAll("\'", "").replaceAll("\"", "").replaceAll("&", "AND").trim().toUpperCase())) {
                        scienceAlerts.setTitle(message.getTitle().replaceAll("\'", "").replaceAll("\"", "").replaceAll("&", "AND").replaceAll("-andgt;", "").trim().toUpperCase());
                        scienceAlerts.setPublishDate(new Date(message.getPubDate().toString()));
                        scienceAlerts.setImportDate(new Date((new Date()).getTime()));
                        String totalSmsStr = message.getTitle().replaceAll("\'", "").replaceAll("\"", "").replaceAll("&", "AND").trim().toUpperCase() + "-" + message.getDescription().replaceAll("\'", "").replaceAll("\"", "").replaceAll("&", "and").trim();
                        totalSmsStr = totalSmsStr.trim();
                        int totalSmsLength = totalSmsStr.trim().length();
                        if(totalSmsLength > Integer.parseInt(PropertyFileReader.getValue("MIN_LENGTH")) && totalSmsLength < Integer.parseInt(PropertyFileReader.getValue("MAX_LENGTH"))) {
                            scienceAlerts.setScheduled(Boolean.valueOf(true));
                            scienceAlerts.setSms(totalSmsStr);
                            this.scienceAlertsBo.save(scienceAlerts);
                        }
                    }
                }
            }

            logger.info("============End of data feeding===============");
        } catch (Exception var11) {
            logger.error(var11);
            var11.printStackTrace();
        }

    }
}
