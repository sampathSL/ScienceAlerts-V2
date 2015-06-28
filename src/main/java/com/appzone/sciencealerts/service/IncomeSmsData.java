//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.service;

import com.appzone.sciencealerts.hibernate.bo.IncomeSmsTransactionBo;
import com.appzone.sciencealerts.hibernate.bo.impl.IncomeSmsTransactionBoImpl;
import com.appzone.sciencealerts.hibernate.model.IncomeSmsTransaction;
import java.util.Date;
import org.apache.log4j.Logger;

public class IncomeSmsData {
    IncomeSmsTransaction incomeSmsTransaction = new IncomeSmsTransaction();
    IncomeSmsTransactionBo incomeSmsTransactionBo = new IncomeSmsTransactionBoImpl();
    private static final Logger logger = Logger.getLogger(IncomeSmsData.class);

    public IncomeSmsData() {
    }

    public void CollectIncomeData(String address, String content, Date date) {
        try {
            this.incomeSmsTransaction.setSenderAddress(address);
            this.incomeSmsTransaction.setSenderSms(content);
            this.incomeSmsTransaction.setTransactionTime(date);
            this.incomeSmsTransactionBo.save(this.incomeSmsTransaction);
        } catch (Exception var5) {
            logger.info(var5.toString());
            var5.printStackTrace();
        }

    }
}
