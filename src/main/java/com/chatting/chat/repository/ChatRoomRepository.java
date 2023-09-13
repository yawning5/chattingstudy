package com.chatting.chat.repository;

import com.chatting.chat.dto.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
/*
basic2
채팅방을 생성하고 정보르 조회하는 Repository를 생성한다 실습에서는 간단하게 만들거라 채팅방 정보를 Map으로 관리하지만
서비스에선s DB나 다른 저장 매체에 채팅방 정보를 저장하도록 구현해야 함.
ChatRoomRepository가 ChatService를 대체할거임
 */
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void  init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createchatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
