package com.muca.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.text.DateFormatter;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final ObjectMapper mapper;
    private final MessageService messageService;

    // iso = ISO.DATE_TIME
    @GetMapping
    public DeferredResult<ResponseEntity<?>> getMessage(
            @RequestParam String lastDateTime) {
        LocalDateTime dateTime = LocalDateTime.parse(lastDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<MessageEntity> messages = messageService.getMessageByLastDateTime(dateTime);
        if (messages.size() > 0) {
            log.debug("new message detected");
            result.setResult(ResponseEntity.ok().body(messages));
        }

        return result;
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody MessageEntity message) {
        // MessageEntity message = mapper.readValue(json, MessageEntity.class);
        return ResponseEntity.ok().body(messageService.save(message));
    }
}
