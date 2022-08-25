package com.muca.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "groups")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity {

    @Id
    @Column(name = "group_id")
    private String groupId;
    @Builder.Default
    @OneToMany(mappedBy = "group")
    private List<MemberEntity> members = new ArrayList<>();
}
