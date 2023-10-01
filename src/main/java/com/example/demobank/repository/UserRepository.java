package com.example.demobank.repository;

import com.example.demobank.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("""
            update User u set u.token = null, u.code = null, u.verified = 1, u.verified_at = NOW(), u.updated_at = NOW(), u.created_at = NOW()
            where u.token = ?1 and u.code = ?2""")
    void updateVerifiedAndVerified_atAndUpdated_atByTokenAndCode(String token, String code);

    @Query(value = "SELECT token from users where token = :token", nativeQuery = true)
    String checkToken(@Param("token")String token);

    @Query(value = "SELECT email from users where email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);

    @Query(value = "SELECT password from users where email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    @Query(value = "SELECT verified from users where email = :email", nativeQuery = true)
    int isVerified(@Param("email")String email);
}
