package com.jackass.RestAPI.entity;

import javax.persistence.*;

@Table(name = "group")
@Entity
public class Group {

    @Id
    @Column(name = "user_group")
    private int userGroup;
    @Column(name = "group_name")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }
}
