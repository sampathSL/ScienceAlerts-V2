//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.SmsTransactionBo;
import com.appzone.sciencealerts.hibernate.bo.impl.SmsTransactionBoImpl;
import com.appzone.sciencealerts.hibernate.model.SmsSender;
import com.appzone.sciencealerts.hibernate.model.SmsTransaction;
import java.util.Date;
import org.apache.log4j.Logger;

public class FinalSms {
    SmsTransaction smsTransaction = new SmsTransaction();
    SmsTransactionBo smsTransactionBo = new SmsTransactionBoImpl();
    private static final Logger logger = Logger.getLogger(FinalSms.class);

    public FinalSms() {
    }

    public void CollectData(SmsSender smsReceiver, String receiverSms, Date transactionTime) {
        try {
            if(smsReceiver != null && smsReceiver.getAddress() != null) {
                this.smsTransaction.setReceiverAddress(smsReceiver.getAddress());
                this.smsTransaction.setReceiverSms(receiverSms);
                this.smsTransaction.setTransactionTime(transactionTime);
                this.smsTransaction.setReceiverAddress(smsReceiver.getAddress());
                this.smsTransaction.setReceiverSms(receiverSms);
                this.smsTransaction.setTransactionTime(transactionTime);
                this.smsTransactionBo.save(this.smsTransaction);
            } else if(smsReceiver == null) {
                logger.error("Unexpected Error");
            }
        } catch (Exception var5) {
            logger.error(var5);
            var5.printStackTrace();
        }

    }

    public void NotAMember(String address, String receiverSms, Date transactionTime) {
        try {
            this.smsTransaction.setReceiverAddress(address);
            this.smsTransaction.setReceiverSms(receiverSms);
            this.smsTransaction.setTransactionTime(transactionTime);
            this.smsTransactionBo.save(this.smsTransaction);
        } catch (Exception var5) {
            logger.error(var5);
            var5.printStackTrace();
        }

    }

    private void writeData(SmsTransaction smsTransaction) {
        logger.info(smsTransaction.getId() + "=============Transaction Id===============");
        logger.info(smsTransaction.getReceiverAddress() + "=============Transaction Receiver Address===============");
        logger.info(smsTransaction.getReceiverSms() + "=============Receiver Sms===============");
        logger.info(smsTransaction.getTransactionTime() + "=============Transaction Time===============");
    }
}
