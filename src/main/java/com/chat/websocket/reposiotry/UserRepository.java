package com.chat.websocket.reposiotry;

import com.chat.websocket.model.Status;
import com.chat.websocket.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
