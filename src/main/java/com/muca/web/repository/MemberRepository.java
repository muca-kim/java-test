package com.muca.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muca.web.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
