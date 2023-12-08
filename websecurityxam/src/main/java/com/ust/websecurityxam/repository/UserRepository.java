package com.ust.websecurityxam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.ust.websecurityxam.entity.User;

// @Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
