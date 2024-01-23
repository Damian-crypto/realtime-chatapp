package com.zeus.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zeus.chatapp.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByGroupId(Long id);

    @Query(
            value = "SELECT group_id FROM tbl_user_group WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Long> findByUserId(Long userId);
}
