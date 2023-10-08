package com.example.demobank.repository;

import com.example.demobank.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByToken(String token);
    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query(value = "SELECT token from users where token = :token", nativeQuery = true)
    String checkToken(@Param("token")String token);


    @Query(value = "SELECT email from users where email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);

    @Query(value = "SELECT password from users where email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    @Query(value = "SELECT verified from users where email = :email", nativeQuery = true)
    int isVerified(@Param("email")String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.token = null, u.code = null, u.isVerified = true, u.verifiedAt = NOW() WHERE u.token = :token AND u.code = :code")
    void updateVerifiedStatusOfUserByTokenAndCode(@Param("token") String token, @Param("code") String code);
}
