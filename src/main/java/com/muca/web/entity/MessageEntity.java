package com.muca.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.muca.web.dto.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageNo;
    private String id;
    private String message;

    public MessageEntity from(MessageDto dto) {
        return MessageEntity.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .build();
    }
}
