package com.muca.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "group")
@Getter
@Builder
public class GroupEntity {

    @Id
    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "group")
    private List<MemberEntity> members = new ArrayList<>();
}
