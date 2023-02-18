package com.iurirest.crudUser.services;

import com.iurirest.crudUser.dto.UsersDTO;
import com.iurirest.crudUser.exceptions.UserAlreadyExistsException;
import com.iurirest.crudUser.exceptions.UserNotFoundException;
import com.iurirest.crudUser.models.Users;
import com.iurirest.crudUser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public UsersDTO updateUser(Long id, Users users) throws UserAlreadyExistsException, UserNotFoundException {
        // Verifica se o CPF já existe no banco de dados
        Optional<Users> userByCpf = userRepository.findByCpf(users.getCpf());
        if (userByCpf.isPresent() && !userByCpf.get().getId().equals(id)) {
            throw new UserAlreadyExistsException("Já existe um usuário com esse CPF");
        }

        Optional<Users> userToUpdate = userRepository.findById(id);
        if (!userToUpdate.isPresent()) {
            throw new UserNotFoundException("Usuário não encontrado");
        }

        Users updatedUser = userToUpdate.get();
        updatedUser.setName(users.getName());
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setCpf(users.getCpf());
        Users savedUser = userRepository.save(updatedUser);

        return new UsersDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCpf(), "Usuário alterado com sucesso");
    }

    public UsersDTO createUser(Users users) throws UserAlreadyExistsException {
        // Verifica se o CPF já existe no banco de dados
        Optional<Users> userByCpf = userRepository.findByCpf(users.getCpf());
        if (userByCpf.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com esse CPF");
        }
        Users createdUser = userRepository.save(users);
        return new UsersDTO(createdUser.getId(), createdUser.getName(), createdUser.getEmail(), createdUser.getCpf(), "Usuário criado com sucesso");
    }

}
