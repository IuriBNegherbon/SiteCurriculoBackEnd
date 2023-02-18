package com.iurirest.crudUser.repositories;

import com.iurirest.crudUser.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByCpf(String cpf);
}
