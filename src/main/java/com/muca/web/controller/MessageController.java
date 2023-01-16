package com.muca.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private ExecutorService bakers = Executors.newFixedThreadPool(5);

    // iso = ISO.DATE_TIME
    @GetMapping
    public DeferredResult<ResponseEntity<?>> getMessage(@RequestParam String lastDateTime) {
        log.debug("last time : " + lastDateTime);
        String[] split = lastDateTime.split(" ");
        String[] date = split[0].split("-");
        String[] time = split[1].split(":");
        log.debug(time[2]);
        String[] sec = time[2].split("\\.");
        // log.debug(date.toString() + time.toString() + sec.toString());
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]),
                Integer.parseInt(sec[0]), Integer.parseInt(sec[1]));
        // LocalDateTime dateTime = LocalDateTime.parse(lastDateTime,
        // DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        bakers.execute(() -> {
            try {
                Thread.sleep(5000);
                log.debug("bakers execute!!");
                List<MessageEntity> messages = messageService.getMessageByLastDateTime(dateTime);
                if (messages.size() > 0) {
                    log.debug("new message detected!!" + messages.size());
                    result.setResult(ResponseEntity.ok().body(messages));
                }
            } catch (Exception e) {
                result.setErrorResult("result get fail!! " + e.getMessage());
            }
        });

        return result;
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody MessageEntity message) {
        // MessageEntity message = mapper.readValue(json, MessageEntity.class);
        messageService.save(message);
        return ResponseEntity.ok().body(null);
    }
}
