package com.example.backend.daos;

import com.example.backend.dtos.UserDto;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public User updateUserPassword(Integer userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId);

        if (user != null && userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username) != null ? userRepository.findByUsername(username) : null;
    }

    public User updateUser(Integer userId, UserDto updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            if (updatedUser.getFirstname() != null) {
                existingUser.setFirstname(updatedUser.getFirstname());
            }
            if (updatedUser.getLastname() != null) {
                existingUser.setLastname(updatedUser.getLastname());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getCareer() != null) {
                existingUser.setCareer(updatedUser.getCareer());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getImageLink() != null) {
                existingUser.setImageLink(updatedUser.getImageLink());
            }

            existingUser.setGender(updatedUser.isGender());

            return userRepository.save(existingUser);
        }
        return null; // User not found
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
        user.setRole(userDto.getRole());

        return userRepository.save(user);
    }
}
