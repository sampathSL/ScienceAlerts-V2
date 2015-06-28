//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appzone.sciencealerts.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "help",
    catalog = "ScienceAlerts"
)
public class Help implements Serializable {
    private Integer id;
    private String helpText;

    public Help() {
    }

    public Help(String helpText) {
        this.helpText = helpText;
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
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(
        name = "HelpText",
        length = 160
    )
    public String getHelpText() {
        return this.helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
}
