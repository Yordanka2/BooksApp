package dany.fmi.master.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dany.fmi.master.entities.UserEntity;

import java.util.List;

import javax.persistence.Entity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUsername(String username);
    UserEntity findUserByUsernameAndPassword(String username, String password);
    
    
}
