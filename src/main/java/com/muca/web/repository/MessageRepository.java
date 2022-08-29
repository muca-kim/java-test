package com.muca.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muca.web.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {

}
