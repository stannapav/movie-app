package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.config.JwtTokenProvider;
import org.example.movieapp.db.dto.UserDTO;
import org.example.movieapp.db.entities.UserModel;
import org.example.movieapp.db.repositories.UserRepository;
import org.example.movieapp.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    public UserDTO getUserById(Integer id){
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    public UserDTO getUserByEmail(String email){
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }
    
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
    
    public void register(UserModel user){
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("This email is already being used");
        }
        
        if(user.getPassword().length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public UserDTO login(String email, String password){
        if (email == null) {
            throw new IllegalArgumentException("Email is required");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password is required");
        }

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        
        String token = jwtTokenProvider.authenticateUser(email);
        UserDTO userDTO = userMapper.toDTO(user);
        userDTO.setToken(token);
        
        return userDTO;
    }
    
    public void updateUser(Integer id, UserModel user){
        UserModel updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Optional<UserModel> optionalUser = userRepository.findByEmail(user.getEmail());
        
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
        UserModel deleteUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(deleteUser);
    }
    
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
