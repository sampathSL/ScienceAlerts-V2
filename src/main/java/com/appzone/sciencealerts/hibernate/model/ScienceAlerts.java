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
    name = "sciencealerts",
    catalog = "ScienceAlerts"
)
public class ScienceAlerts implements Serializable {
    private Long id;
    private String title;
    private String sms;
    private Date importDate;
    private Date publishDate;
    private Boolean scheduled;

    public ScienceAlerts() {
    }

    public ScienceAlerts(String title, String sms, Boolean scheduled) {
        this.title = title;
        this.sms = sms;
        this.scheduled = scheduled;
    }

    public ScienceAlerts(String title, String sms, Date importDate, Date publishDate, Boolean scheduled) {
        this.title = title;
        this.sms = sms;
        this.importDate = importDate;
        this.publishDate = publishDate;
        this.scheduled = scheduled;
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
        name = "Title",
        nullable = false,
        length = 160
    )
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(
        name = "Sms",
        nullable = false,
        length = 160
    )
    public String getSms() {
        return this.sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "ImportDate",
        length = 19
    )
    public Date getImportDate() {
        return this.importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "PublishDate",
        length = 19
    )
    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Column(
        name = "Scheduled"
    )
    public Boolean getScheduled() {
        return this.scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }
}
