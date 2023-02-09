package com.iurirest.crud.controllers;

import com.iurirest.crud.dto.UsersDTO;
import com.iurirest.crud.models.Users;
import com.iurirest.crud.repositories.UserRepository;
import com.iurirest.crud.services.UserService;
import com.iurirest.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) {
            UsersDTO dto = new UsersDTO();
            dto.setMessage("Usuário não encontrado");
            return ResponseEntity.ok(dto);
        }
        Users user = optionalUsers.get();

        UsersDTO response = new UsersDTO(user.getId(), user.getName(), user.getEmail(), user.getCpf());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAll() {
        List<Users> users = userService.getAllUsers();
        List<UsersDTO> usersDTO = users.stream().map(user -> new UsersDTO(user.getId(), user.getName(), user.getEmail(), user.getCpf())).collect(Collectors.toList());
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody Users users) {
        users = userRepository.save(users);
        UsersDTO UsersDTO = new UsersDTO(users.getId(), users.getName(), users.getEmail(), users.getCpf(), "Usuário criado com sucesso");
        return new ResponseEntity<>(UsersDTO, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id, @Valid @RequestBody Users users) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            UsersDTO dto = new UsersDTO();
            dto.setMessage("Usuário não encontrado");
            return ResponseEntity.ok(dto);
        }

        Users updatedUser = user.get();
        updatedUser.setName(users.getName());
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setCpf(users.getCpf());
        userRepository.save(updatedUser);

        UsersDTO UsersDTO = new UsersDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getCpf(), "Usuário alterado com sucesso");
        return ResponseEntity.ok(UsersDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(new ResponseMessage("Usuário excluído com sucesso"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("Usuário não encontrado"));
    }
}
