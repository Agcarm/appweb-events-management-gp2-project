package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gp2.appwebeventsmanagementgp2.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail (String email);
}