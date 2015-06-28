//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
    name = "smstransaction",
    catalog = "ScienceAlerts"
)
public class SmsTransaction implements Serializable {
    private Long id;
    private String receiverAddress;
    private String receiverSms;
    private Date transactionTime;

    public SmsTransaction() {
    }

    public SmsTransaction(String receiverAddress, String receiverSms, Date transactionTime) {
        this.receiverAddress = receiverAddress;
        this.receiverSms = receiverSms;
        this.transactionTime = transactionTime;
    }

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(
        name = "Id",
        unique = true,
        nullable = false
    )
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(
        name = "ReceiverAddress",
        length = 100
    )
    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @Column(
        name = "ReceiverSms",
        length = 160
    )
    public String getReceiverSms() {
        return this.receiverSms;
    }

    public void setReceiverSms(String receiverSms) {
        this.receiverSms = receiverSms;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "TransactionTime",
        length = 19
    )
    public Date getTransactionTime() {
        return this.transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
