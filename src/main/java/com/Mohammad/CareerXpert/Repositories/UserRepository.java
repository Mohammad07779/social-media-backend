package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailIgnoreCase(String email);

    Optional<Users> findByUserNameIgnoreCase(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);



    @Query("SELECT u FROM Users u WHERE LOWER(u.userName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Users> searchUserByName(@Param("name") String name);

//    @Query("SELECT COUNT(u) FROM User u")
//    Integer countAllUsers();

}
