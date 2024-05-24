package com.chat.websocket.reposiotry;

import com.chat.websocket.model.ChatMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {


    List<ChatMessage> findByChatId(String s);
}
