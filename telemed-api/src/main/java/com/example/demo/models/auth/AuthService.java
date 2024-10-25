package com.example.demo.models.auth;

import com.example.demo.config.JwtUtil;
import com.example.demo.models.auth.dtos.LoginDto;
import com.example.demo.models.auth.dtos.LoginResponseDto;
import com.example.demo.models.auth.dtos.RegisterDto;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserRepository;
import com.example.demo.models.user.dtos.GetUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public GetUserDto registerUser(RegisterDto registerDTO) {
        User user = modelMapper.map(registerDTO, User.class);
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return modelMapper.map(userRepository.save(user), GetUserDto.class);
    }

    public LoginResponseDto loginUser(LoginDto loginDTO) throws Exception {
        User user = userRepository.findByEmailAddress(loginDTO.getEmailAddress())
                .orElseThrow(() -> new Exception("Invalid credentials"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmailAddress());
        LoginResponseDto response = modelMapper.map(user, LoginResponseDto.class);
        response.setToken(token);

        return response;
    }
}
