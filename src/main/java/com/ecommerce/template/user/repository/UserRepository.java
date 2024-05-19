package com.ecommerce.template.user.repository;

import com.ecommerce.template.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findByIdLikeAndNameLikeAndCreateTimeBetween(String id, String name, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
