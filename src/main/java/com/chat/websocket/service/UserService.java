package com.chat.websocket.service;

import com.chat.websocket.model.Status;
import com.chat.websocket.reposiotry.UserRepository;
import com.chat.websocket.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user){
           var storedUser = userRepository.findById(user.getNickName())
               .orElse(null);
           if(storedUser!=null){
               storedUser.setStatus(Status.OFFLINE);
               userRepository.save(storedUser);
           }

    }

    public List<User> findConnectedUser(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }


}
