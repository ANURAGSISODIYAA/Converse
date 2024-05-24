package com.chat.websocket.reposiotry;

import com.chat.websocket.model.ChatRoom;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomReposiotry extends MongoRepository<ChatRoom,String> {

    Optional<ChatRoom> findBySenderIdAndRecipientsId(String senderId, String recipientId);
}
