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
    name = "incomesmstransaction",
    catalog = "ScienceAlerts"
)
public class IncomeSmsTransaction implements Serializable {
    private Long id;
    private String senderAddress;
    private String senderSms;
    private Date transactionTime;

    public IncomeSmsTransaction() {
    }

    public IncomeSmsTransaction(String senderAddress, String senderSms, Date transactionTime) {
        this.senderAddress = senderAddress;
        this.senderSms = senderSms;
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
        name = "SenderAddress",
        length = 100
    )
    public String getSenderAddress() {
        return this.senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @Column(
        name = "SenderSms",
        length = 160
    )
    public String getSenderSms() {
        return this.senderSms;
    }

    public void setSenderSms(String senderSms) {
        this.senderSms = senderSms;
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
