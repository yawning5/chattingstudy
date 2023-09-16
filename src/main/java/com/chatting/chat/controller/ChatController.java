package com.chatting.chat.controller;

import com.chatting.chat.dto.ChatMessage;
import com.chatting.chat.dto.ChatRoom;
import com.chatting.chat.pubsub.RedisPublisher;
import com.chatting.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/chat") -> basic2의 변경점
/*
basic2
publisher구현
@MessageMapping을 통해 WebSocket으로 들어오는 메시지 발행을 처리한다
클라이언트에서 prefix를 붙여서 /pub/chat/message로 발행요청을 하면 Controller가 해당 메시지를 받아 처리한다
메시지가 발행되면 /sub/chat/room/{roomId}로 메시지를 send하는 것을 볼 수 있는데
클라이언트에서는 해당 주소를 (/sub/chat/room/{roomId}) 구독(subscribe)하고 있다가 메시지가 전달되면 화면에 출력하면 된다.
여기서 /sub/chat/room/{roomId}는 채팅룸을 구분하는 값이므로 pub/sub에서 Topic의 역할이라고 보면된다.
기존의 WebSocketHandler가 했던 역할을 대체하므로 WebSocketHandler는 삭제
 */
/**
 * basic3
 * 클라이언트가 채팅방 입장시 채팅방(topic)에서 대화가 가능하도록 리스너를 연동하는
 * enterChatRoom 메서드를 세팅한다. 채팅방에 발행된 메시지는
 * 서로 다른 서버에 공유하기 위해 redis의 Topic으로 발행
 */
public class ChatController {

//    private final SimpMessageSendingOperations messagingTemplate;
    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다 (publish)
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
    /*
    구독자(subscriber)구현
    서버단에서 따로 추가할 구현이 없다. 웹뷰에서 stomp 라이브러리를 이용해서 subscriber 주소를 바라보고 있는 코드만 작성하면 됨
     */
}
