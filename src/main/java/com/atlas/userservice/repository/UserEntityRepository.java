package com.atlas.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atlas.userservice.repository.entity.UserEntity;


/*
 * Repository interface for userEntity provides CRUD Operations without
 * implementations
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmailIdAndPassword(String emailid,String password);

}
