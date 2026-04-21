package com.atlas.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atlas.userservice.repository.entity.UserEntity;

@Repository
/*
 * Repository interface for userEntity
 * provides CRUD Operations without implementations 
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	

}
