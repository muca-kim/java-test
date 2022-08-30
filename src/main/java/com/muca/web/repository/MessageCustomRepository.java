package com.muca.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.muca.web.entity.MessageEntity;

public interface MessageCustomRepository {
    List<MessageEntity> findByCreatedDate(LocalDateTime lastDateTime);
}
