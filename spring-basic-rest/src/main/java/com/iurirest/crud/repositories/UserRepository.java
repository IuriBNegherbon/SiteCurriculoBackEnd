package com.iurirest.crud.repositories;

import com.iurirest.crud.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    //jdbcTemplate.update("insert into users(name, email, password) values (?,?,?)", user.getName(), user.getEmail(), user.getPassword());
}
