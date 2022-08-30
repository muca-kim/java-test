package com.muca.web.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.muca.web.entity.MessageEntity;
import com.muca.web.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<MessageEntity> getMessageByLastDateTime(LocalDateTime lastDateTime) {
        return messageRepository.findByCreatedDate(lastDateTime);
    }

    public MessageEntity save(MessageEntity entity) {
        return messageRepository.save(entity);
    }
}
