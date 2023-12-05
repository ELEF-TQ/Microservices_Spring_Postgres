package com.Elef.userservice.service;

import com.Elef.userservice.entity.User;
import com.Elef.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setDepartmentId(updatedUser.getDepartmentId());
            return userRepo.save(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
