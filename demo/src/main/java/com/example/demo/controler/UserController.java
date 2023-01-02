package com.example.demo.controler;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
        System.out.println("Add success");
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUsingVariable(@PathVariable Long id){
        return new ResponseEntity<User>(userService.read(id) , HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUsingParam(@RequestParam("id") Long id){
        return new ResponseEntity<User>(userService.read(id) , HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@RequestBody User user , @PathVariable Long id){
        return new ResponseEntity<User>(userService.update(user, id),HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws ResourceNotFoundException {
        userService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
