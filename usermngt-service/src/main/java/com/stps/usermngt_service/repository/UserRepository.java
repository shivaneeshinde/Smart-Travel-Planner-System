package com.stps.usermngt_service.repository;

import com.stps.usermngt_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
