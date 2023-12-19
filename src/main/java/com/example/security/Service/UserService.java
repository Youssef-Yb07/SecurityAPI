package com.example.security.Service;

import com.example.security.Entity.CreateUserDTO;
import com.example.security.Entity.User;
import com.example.security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public User save(CreateUserDTO userDTO) {

        User user=new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setProfilePicturePath(userDTO.getProfilePicturePath());

        return userRepository.save(user);
    }
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByRole(String role) {
        List<User> users = new ArrayList<>();
        for (User user:findAll()) {

            //.name() is used to get the name of the enum

            if(user.getRole().name().equals(role)) {
                System.out.println("User ==>"+user);
                users.add(user);
            }
        }
        return users;
    }
    public List<User> deleteAll() {

        List<User> users = findByRole("USER");

        System.out.println("Users ==>"+users);

        userRepository.deleteAll(users);

        return users;
    }

    public User updateUser(Integer id,User user){
        User u=userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));

        user.setId(u.getId());

        return userRepository.save(user);
    }
    public User deleteById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        userRepository.deleteById(id);
        return user;
    }

 /*   public void uploadProfilePicture(Integer userId, MultipartFile file) throws IOException {
        String profilePicturePath = saveProfilePicture(file, userId);
        updateProfilePicturePath(userId, profilePicturePath);
    }

    private String saveProfilePicture(MultipartFile file, Integer userId) throws IOException {
        String fileName = "profile_picture_" + userId + "_" + System.currentTimeMillis() + ".jpg"; // Exemple de nom de fichier
        String filePath = "/path/to/your/profile/pictures/" + fileName; // Changez cela avec votre chemin
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    private void updateProfilePicturePath(Integer userId, String profilePicturePath) {
        User user = findById(userId);
        user.setProfilePicturePath(profilePicturePath);
        userRepository.save(user);
    }*/


}
