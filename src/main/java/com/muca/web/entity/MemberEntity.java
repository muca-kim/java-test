package com.muca.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.muca.web.constants.Grade;
import com.muca.web.constants.Sex;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
@Builder
public class MemberEntity {

    @Id
    private String name;
    private int age;
    private Sex sex;
    private Grade grade;
    @ManyToOne
    @JoinColumn(name = "name")
    private GroupEntity group;

    public void setGroup(GroupEntity group) {
        this.group = group;
        if (!group.getMembers().contains(this)) {
            group.getMembers().add(this);
        }
    }
}
