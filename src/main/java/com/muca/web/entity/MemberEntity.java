package com.muca.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.muca.web.constants.Grade;

import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {

    @Id
    private String name;
    private int age;
    private String sex;
    private Grade grade;
}
