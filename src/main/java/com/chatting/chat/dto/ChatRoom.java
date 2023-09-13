package com.chatting.chat.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
/*
basic1
채팅방은 입장한 클라이언트들의 정보를 가지고 있어야 하므로 WebsocketSession 정보 리스트를 멤버필드로 갖는다
나머지 멤버 필드로 채팅방 id, 채팅방 이름을 추가함
채팅방에는 입장, 대화하기의 기능이 있으므로 handleAction을 통해 분기 처리함(if 로 Enter면 님이 입장했습니다 출력하게 하는걸 말하는 듯)
사용자가 채팅방에 입장하게 되면 Chatroom의 sessions에 해당 사용자의 웹소켓 세션정보(WebSocketSession)가 추가된다
이로써 채팅방에 어떤 클라이언트들이 있는지 알 수 있게 됨 그리고 채팅룸에 사용자 중 한명이 메시지를 보냈을 때
채팅룸의 모든 session에 메시지를 발송하면 채팅이 완성된다.
 */

/*
basic2
pub/sub 방식을 이용하면 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어진다.
또한 발송의 구현도 알아서 해결되서 일일이 클라이언트에게 메시지를 발송하는 구현 필요 없어진다.
 */
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}
