package com.muca.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.muca.web.dto.MessageDto;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "message")
@Getter
@Builder
public class MessageEntity extends BaseTimeEntity {

    @Id
    private String id;
    private String message;

    public MessageEntity from(MessageDto dto) {
        return MessageEntity.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .build();
    }
}
