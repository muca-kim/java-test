package com.muca.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muca.web.entity.MessageEntity;
import com.muca.web.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final ObjectMapper mapper;
    private final MessageService messageService;

    @GetMapping
    public DeferredResult<ResponseEntity<?>> getMessage(
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime lastDateTime) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<MessageEntity> messages = messageService.getMessageByLastDateTime(lastDateTime);
        if (messages.size() > 0) {
            result.setResult(ResponseEntity.ok().body(messages));
        }

        return result;
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        MessageEntity message = mapper.readValue(json, MessageEntity.class);
        return ResponseEntity.ok().body(messageService.save(message));
    }
}
