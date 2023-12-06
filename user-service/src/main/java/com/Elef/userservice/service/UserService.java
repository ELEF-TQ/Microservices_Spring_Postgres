package com.Elef.userservice.service;

import com.Elef.userservice.entity.User;
import com.Elef.userservice.repository.UserRepo;
import com.Elef.userservice.vo.Department;
import com.Elef.userservice.vo.ResponseTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;

    public List<User> getAllUsers() {
        return userRepo.findAll();
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

    public ResponseTemplateVo getUserWithDep(Long id) {
        ResponseTemplateVo vo = new ResponseTemplateVo();
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
            vo.setUser(user);
            vo.setDepartment(department);
        } else {

        }
        return vo;
    }



}
