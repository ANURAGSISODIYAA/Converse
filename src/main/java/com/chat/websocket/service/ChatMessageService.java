package com.chat.websocket.service;

import com.chat.websocket.model.ChatMessage;
import com.chat.websocket.reposiotry.ChatMessageRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRomId(chatMessage.getSenderId(),chatMessage.getRecipientId(),true)
            .orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){

        var chatId = chatRoomService.getChatRomId(senderId,
            recipientId,false);

        return chatId.map(chatMessageRepository :: findByChatId).orElse(new ArrayList<>());
    }


}
