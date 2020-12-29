package com.gtx.backend.repositories;

import com.gtx.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    //We dont need any methods for C R U Delete since it extends CrudOperations
    UserEntity findUserByEmail(String email);

    UserEntity findByUserId(String id);
}
