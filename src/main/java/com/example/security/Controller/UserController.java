package com.example.security.Controller;

import com.example.security.Entity.CreateUserDTO;
import com.example.security.Entity.User;
import com.example.security.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> CreateUser(@RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @GetMapping("/get/by/id/{id}")
    public ResponseEntity<User> GetUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> GetAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> DeleteUser(@PathVariable Integer id) {

        return ResponseEntity.ok(userService.deleteById(id));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<List<User>> DeleteAllUsers() {
       return ResponseEntity.ok(userService.deleteAll());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

  /*  @PostMapping("/users/{userId}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userService.uploadProfilePicture(userId, file));
    }*/
}
