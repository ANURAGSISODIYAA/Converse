package com.chat.websocket.service;

import com.chat.websocket.model.ChatRoom;
import com.chat.websocket.reposiotry.ChatRoomReposiotry;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomReposiotry chatRoomReposiotry;

    public Optional<String> getChatRomId(String senderId, String recipientId, boolean createNewRoomIfNotExits) {

        return chatRoomReposiotry.findBySenderIdAndRecipientsId(senderId, recipientId).map(ChatRoom::getChatId).or(() -> {
            if (createNewRoomIfNotExits) {
                var chatId = createChatId(senderId, recipientId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });
    }

    private String createChatId(String senderId, String recipientId) {

        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
            .chatId(chatId)
            .senderId(senderId)
            .recipientsId(recipientId)
            .build();

        ChatRoom recipientSender = ChatRoom.builder()
            .chatId(chatId)
            .senderId(recipientId)
            .recipientsId(senderId)
            .build();

        chatRoomReposiotry.save(senderRecipient);
        chatRoomReposiotry.save(recipientSender);

        return chatId;
    }

}
