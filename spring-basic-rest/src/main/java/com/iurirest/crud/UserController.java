package com.iurirest.crud;

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

    /*@PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody Users users) {
        Users createdUser = userRepository.save(users);
        UserResponseDTO createdUserDTO = new UserResponseDTO();
        createdUserDTO.setId(createdUser.getId());
        createdUserDTO.setName(createdUser.getName());
        createdUserDTO.setEmail(createdUser.getEmail());
        //createdUserDTO.setPassword(createdUser.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }*/
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        Users user = new Users(userRequestDTO.getName(), userRequestDTO.getEmail());
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setMessage("Usuário não encontrado");
            return ResponseEntity.ok(dto);
        }
        Users user = optionalUsers.get();

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        //response.setPassword(user.getPassword());
        return ResponseEntity.ok(response);
    }

    /*@GetMapping
    public ResponseEntity<List<Users>> getAll() {
        UserService userService = new UserService();
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<Users> users = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOS = users.stream().map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody Users users) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setMessage("Usuário não encontrado");
            return ResponseEntity.ok(dto);
            //return ResponseEntity.badRequest().body(new ResponseMessage("Usuário não encontrado"));
        }

        Users updatedUser = user.get();
        updatedUser.setName(users.getName());
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        userRepository.save(updatedUser);

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(updatedUser.getId());
        dto.setName(updatedUser.getName());
        dto.setEmail(updatedUser.getEmail());
        //dto.setPassword(updatedUser.getPassword());
        return ResponseEntity.ok(dto);
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
