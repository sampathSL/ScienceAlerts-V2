//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.model;

import com.appzone.sciencealerts.hibernate.model.SmsSender;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
    name = "appcomments",
    catalog = "ScienceAlerts"
)
public class AppComments implements Serializable {
    private Long id;
    private SmsSender smssender;
    private String comment;
    private Boolean isChecked;

    public AppComments() {
    }

    public AppComments(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public AppComments(SmsSender smssender, String comment, Boolean isChecked) {
        this.smssender = smssender;
        this.comment = comment;
        this.isChecked = isChecked;
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

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "SenderId"
    )
    public SmsSender getSmssender() {
        return this.smssender;
    }

    public void setSmssender(SmsSender smssender) {
        this.smssender = smssender;
    }

    @Column(
        name = "COMMENT",
        length = 500
    )
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(
        name = "IsChecked"
    )
    public Boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}
