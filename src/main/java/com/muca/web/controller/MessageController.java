package com.muca.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muca.web.entity.MessageEntity;
import com.muca.web.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final ObjectMapper mapper;
    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<?> saveMessage(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        MessageEntity message = mapper.readValue(json, MessageEntity.class);
        return ResponseEntity.ok().body(messageService.save(message));
    }
}
