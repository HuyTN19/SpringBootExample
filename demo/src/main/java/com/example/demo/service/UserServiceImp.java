package com.example.demo.service;

import com.example.demo.Exception.ItemAlreadyExistsException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.example.demo.reponsitory.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("User is already register with email: " + userModel.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(userModel, newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User read(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found for id :" +id));
    }

    @Override
    public User update(User user, Long id) throws ResourceNotFoundException {
        User existUser = read(id);
        existUser.setName(user.getName() != null ? user.getName() : existUser.getName());
        existUser.setEmail(user.getEmail() != null ? user.getEmail(): existUser.getEmail());
        existUser.setPassword(user.getPassword() != null ? user.getPassword() : existUser.getPassword());
        existUser.setAge(user.getAge() != null ? user.getAge() : existUser.getAge());
        return userRepository.save(existUser);
    }

    @Override
    public void delete(Long id) {
        User user = read(id);
        userRepository.delete(user);
    }
}
