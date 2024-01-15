package com.zeus.chatapp.repository;

import com.zeus.chatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository // indicates this component is responsible for accessing and manipulating data from a database
public interface UserRepository extends JpaRepository<User, Long> {
    // Query Creation: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<User> findByNameContaining(String name);

    List<User> findByMobileNo(String mobileNo);

    @Transactional // meaning that any database operations performed within the marked method will be executed within a transaction. If the transaction is unsuccessful, the changes will be rollback (not save).
    @Modifying // used to enhance the @Query annotation so that we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries
    @Query( // mechanism to define custom JPQL (Java Persistence Query Language) and native SQL queries directly on repository methods.
            value = "UPDATE tbl_user SET name = ?1 WHERE mobile_no = ?2",
            nativeQuery = true
    )
    void updateNameByMobileNo(String name, String mobileNo);

    Optional<User> findUserByEmail(String email);
}
