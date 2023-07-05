package com.example.backend.daos;

import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        // phương thức findAll() bên dưới thuộc interface JpaRepository được kế thừa
        // bởi interface UserRepository
        Streamable.of(userRepository.findAll()).forEach(users::add);

        return users;
    }
}





