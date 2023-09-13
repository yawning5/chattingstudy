package com.chatting.chat.controller;

import com.chatting.chat.dto.ChatRoom;
import com.chatting.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    // 여기서 name -> 채팅방 이름이다
    @PostMapping
    public ChatRoom creatRoom(@RequestParam String name) {
        return chatService.creatRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
