package com.chatting.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
채팅 메시지 구현 -> 채팅 메시지를 주고받기 위한 Dto를 만든다
채팅방에 입장, 메시지 보내기 두가지 상황이있어 2가지 enum을 선언
 */
public class ChatMessage {
    // 메시지 타입: 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
