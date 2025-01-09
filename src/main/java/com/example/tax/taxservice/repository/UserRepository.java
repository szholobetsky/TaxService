package com.example.tax.taxservice.repository;

import com.example.tax.taxservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
