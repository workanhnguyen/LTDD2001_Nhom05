package com.example.backend.daos;

import com.example.backend.dtos.AccountTypeDto;
import com.example.backend.dtos.UserDto;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserDao {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream().map((a) -> modelMapper.map(a, UserDto.class))
                .collect(Collectors.toList());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addNewUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            return null;
        }

        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCareer(userDto.getCareer());
        user.setGender(userDto.isGender());
        user.setImageLink(userDto.getImageLink());

        return userRepository.save(user);
    }
}
