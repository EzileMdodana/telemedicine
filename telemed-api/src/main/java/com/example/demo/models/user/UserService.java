package com.example.demo.models.user;
import com.example.demo.models.user.dtos.CreateUserDto;
import com.example.demo.models.user.dtos.GetUserDto;
import com.example.demo.models.user.dtos.UpdateUserDto;
import com.example.demo.models.user.profiles.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetUserDto> getAllUsers() {
        ArrayList<User> users = new ArrayList<>(userRepository.findAll());
        return users.stream().map(user -> modelMapper.map(user, GetUserDto.class)).toList();
    }

    public Optional<GetUserDto> getUserById(UUID id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), GetUserDto.class));
    }

    public GetUserDto createUser(CreateUserDto user) {
        User newUser = userRepository.save(modelMapper.map(user, User.class));
        return modelMapper.map(newUser, GetUserDto.class);
    }

    public User updateUser(UUID id, UpdateUserDto updateUserDto) {
        //TODO: use model mapper
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
