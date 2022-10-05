package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);


    @Query("SELECT NEW com.cg.model.dto.UserDTO (" +
                "u.id, " +
                "u.username" +
            ") " +
            "FROM User AS u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);

    @Query("SELECT NEW com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.phone" +
            ") " +
            "FROM User AS u " +
            "WHERE u.phone = ?1")
    Optional<UserDTO> findUserDTOByPhone(String phone);

    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
            "u.id ," +
            "u.fullName, " +
            "u.username, " +
            "u.password," +
            "u.phone," +
            "u.address," +
            "u.role ," +
            "u.deleted) " +
            "FROM User AS u")
    List<UserDTO> findAllUserDTO();

    @Modifying
    @Query("UPDATE User AS u SET u.deleted = true WHERE u.id = :id")
    void blockUserById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User AS u SET u.deleted = false WHERE u.id = :id")
    void unLockUserById(@Param("id") Long id);

    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
            "u.id ," +
            "u.fullName, " +
            "u.username, " +
            "u.password," +
            "u.phone," +
            "u.address," +
            "u.role ," +
            "u.deleted) " +
            "FROM User AS u WHERE u.id = :id")
    Optional<UserDTO> findUserDTOById(@Param("id") Long id);
    @Query("SELECT NEW com.cg.model.dto.UserDTO(" +
            "u.id ," +
            "u.fullName, " +
            "u.username, " +
            "u.password," +
            "u.phone," +
            "u.address," +
            "u.role ," +
            "u.deleted) " +
            "FROM User AS u WHERE u.username = :username")
    Optional<UserDTO> findUserDTOByUserName(@Param("username") String username);
}
