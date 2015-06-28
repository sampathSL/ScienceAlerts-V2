//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.model;

import com.appzone.sciencealerts.hibernate.model.AppComments;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
    name = "smssender",
    catalog = "ScienceAlerts"
)
public class SmsSender implements Serializable {
    private Long id;
    private String address;
    private String userName;
    private Boolean isReg;
    private Long marks;
    private Boolean isSchedularActive;
    private Date joinedDate;
    private Date lastActiveTime;
    private Boolean isActive;
    private Set<AppComments> appcommentses = new HashSet(0);

    public SmsSender() {
    }

    public SmsSender(String address, Boolean isReg, Boolean isSchedularActive, Boolean isActive) {
        this.address = address;
        this.isReg = isReg;
        this.isSchedularActive = isSchedularActive;
        this.isActive = isActive;
    }

    public SmsSender(String address, String userName, Boolean isReg, Long marks, Boolean isSchedularActive, Date joinedDate, Date lastActiveTime, Boolean isActive, Set<AppComments> appcommentses) {
        this.address = address;
        this.userName = userName;
        this.isReg = isReg;
        this.marks = marks;
        this.isSchedularActive = isSchedularActive;
        this.joinedDate = joinedDate;
        this.lastActiveTime = lastActiveTime;
        this.isActive = isActive;
        this.appcommentses = appcommentses;
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
        name = "Address",
        nullable = false,
        length = 100
    )
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(
        name = "UserName",
        length = 100
    )
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(
        name = "IsReg"
    )
    public Boolean getIsReg() {
        return this.isReg;
    }

    public void setIsReg(Boolean isReg) {
        this.isReg = isReg;
    }

    @Column(
        name = "Marks"
    )
    public Long getMarks() {
        return this.marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    @Column(
        name = "IsSchedularActive"
    )
    public Boolean getIsSchedularActive() {
        return this.isSchedularActive;
    }

    public void setIsSchedularActive(Boolean isSchedularActive) {
        this.isSchedularActive = isSchedularActive;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "JoinedDate",
        length = 19
    )
    public Date getJoinedDate() {
        return this.joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "LastActiveTime",
        length = 19
    )
    public Date getLastActiveTime() {
        return this.lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    @Column(
        name = "IsActive"
    )
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @OneToMany(
        cascade = {CascadeType.ALL},
        fetch = FetchType.LAZY,
        mappedBy = "smssender"
    )
    public Set<AppComments> getAppcommentses() {
        return this.appcommentses;
    }

    public void setAppcommentses(Set<AppComments> appcommentses) {
        this.appcommentses = appcommentses;
    }
}
