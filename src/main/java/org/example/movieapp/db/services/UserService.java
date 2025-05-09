package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.UserDTO;
import org.example.movieapp.db.entities.User;
import org.example.movieapp.db.repositories.UserRepository;
import org.example.movieapp.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserDTO getUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    public UserDTO getUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }
    
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
    
    public void createUser(User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        
        if (optionalUser.isEmpty()) {
            userRepository.save(user);
        }
        else{
            throw new IllegalArgumentException("This email is already being used");
        }
    }
    
    public void updateUser(Integer id, User user){
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        
        if(optionalUser.isEmpty()) {
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
        }
        else if(updateUser.getEmail().equals(user.getEmail())) {
            updateUser.setName(user.getName());
            updateUser.setPassword(user.getPassword());
        }
        else{
            throw new IllegalArgumentException("This email is already being used");
        }
        
        userRepository.save(updateUser);
    }
    
    public void deleteUser(Integer id){
        User deleteUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(deleteUser);
    }
    
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
