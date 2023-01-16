package com.muca.web.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muca.web.entity.MessageEntity;
import com.muca.web.repository.MessageCustomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageCustomRepository messageRepository;
    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<MessageEntity> getMessageByLastDateTime(LocalDateTime lastDateTime) {
        return messageRepository.findByCreatedDate(lastDateTime);
    }

    @Transactional
    public void save(MessageEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }
}
