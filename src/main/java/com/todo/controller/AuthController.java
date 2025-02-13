package com.todo.controller;

import com.todo.dto.LoginDto;
import com.todo.dto.RegisterDto;
import com.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
@PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        String response=authService.login(loginDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
