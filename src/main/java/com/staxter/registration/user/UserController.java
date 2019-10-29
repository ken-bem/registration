package com.staxter.registration.user;

import com.staxter.registration.models.User;
import com.staxter.registration.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    public final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userservice/register")
    public ResponseEntity registerUser(@RequestBody User user){

       User newUser = userService.createUser(user);

       return new ResponseEntity<>(new UserDto(newUser), HttpStatus.CREATED);
    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new LoginDto());
        return "login";
    }

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }
}
